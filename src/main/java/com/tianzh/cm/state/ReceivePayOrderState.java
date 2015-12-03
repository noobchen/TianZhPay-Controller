package com.tianzh.cm.state;

import com.tianzh.cm.annotation.Consumer;
import com.tianzh.cm.constant.SystemConstants;
import com.tianzh.cm.domain.message.DomainEventHandler;
import com.tianzh.cm.event.disuptor.EventDisruptor;
import com.tianzh.cm.model.PayOrder;
import com.tianzh.cm.service.init.GenUserTokenService;
import com.tianzh.cm.service.model.UserTokenBean;
import com.tianzh.cm.service.thirdpay.ChargeOrderService;
import com.tianzh.cm.service.model.FeePoint;
import com.tianzh.cm.service.model.ChargeOrderBean;
import com.tianzh.cm.service.model.ThPayProperty;
import com.tianzh.cm.util.area.AreaUtils;
import com.tianzh.cm.util.http.HttpUtils;
import com.tianzh.cm.util.order.OrderUtils;
import com.tianzh.cm.util.phone.PhoneUtils;
import com.tianzh.cm.util.token.TokenUtils;
import com.tianzh.cm.util.weight.WeightUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by pig on 2015-09-10.
 */
@Consumer("receivePayOrderState")
public class ReceivePayOrderState implements DomainEventHandler {
    Logger emptyProvinceLogger = LoggerFactory.getLogger("empty_province");
    Logger emptyProviderLogger = LoggerFactory.getLogger("empty_provider");
    Logger emptyThpayLogger = LoggerFactory.getLogger("empty_thpay");
    Logger thpayOrderDaoLogger = LoggerFactory.getLogger("thpayorder_dao");
    Logger genUserTokenDaoLogger = LoggerFactory.getLogger("gentoken_dao");

    ChargeOrderService chargeOrderService;

    public void setChargeOrderService(ChargeOrderService chargeOrderService) {
        this.chargeOrderService = chargeOrderService;
    }

    GenUserTokenService genUserTokenService;

    public void setGenUserTokenService(GenUserTokenService genUserTokenService) {
        this.genUserTokenService = genUserTokenService;
    }

    @Override
    public void onEvent(EventDisruptor event, boolean endOfBatch) throws Exception {
        PayOrder payOrder = (PayOrder) event.getDomainMessage().getEventSource();

        String userToken = payOrder.getUserToken();

        if (StringUtils.isEmpty(userToken)) {
            userToken = TokenUtils.generateUserToken(payOrder.getAppId(), payOrder.getMac(), payOrder.getImei());
            payOrder.setUserToken(userToken);
            //存入数据库
            UserTokenBean userTokenBean = new UserTokenBean();

            userTokenBean.setAppid(payOrder.getAppId());
            userTokenBean.setChannel(payOrder.getChannel());
            userTokenBean.setUserToken(userToken);
            userTokenBean.setCreateTime(Calendar.getInstance().getTime());
            userTokenBean.setStatus(SystemConstants.USERFULLTOKEN);

            try {
                genUserTokenService.insertUserToken(userTokenBean);

            } catch (Exception e) {
                e.printStackTrace();
                genUserTokenDaoLogger.error("insert Usertoken:{} fail exception:{}", userTokenBean, ExceptionUtils.getMessage(e));
            }
        }

        //生成此次订单的流水号
        payOrder.setTianzhOrderId(OrderUtils.getPayOrderId());

        String province = payOrder.getProvince();

        if (StringUtils.isEmpty(province) || province.equalsIgnoreCase("null")) {
            //省份信息为空，记录日志，返回默认支付方式
            emptyProvinceLogger.warn("receiver order:{} by empty province info ", payOrder);
            payOrder.zhuquePay();
            return;
        } else {
            if (province.contains("省")) {
                province = province.substring(0, province.lastIndexOf("省"));
            } else if (province.endsWith("壮族自治区")) {
                province = province.substring(0, province.lastIndexOf("壮族自治区"));
            } else if (province.endsWith("回族自治区")) {
                province = province.substring(0, province.lastIndexOf("回族自治区"));
            } else if (province.endsWith("维吾尔自治区")) {
                province = province.substring(0, province.lastIndexOf("维吾尔自治区"));
            } else if (province.endsWith("市")) {
                province = province.substring(0, province.lastIndexOf("市"));
            } else if (province.endsWith("自治区")) {
                province = province.substring(0, province.lastIndexOf("自治区"));
            }

            payOrder.setProvince(province);

            String city = payOrder.getCity();

            if (StringUtils.isNotEmpty(city)) {
                if (city.contains("市")) {
                    city = city.substring(0, city.lastIndexOf("市"));
                    payOrder.setCity(city);
                }
            }
        }

        //判断运营商
        String imsi = payOrder.getImsi();

        if (StringUtils.isEmpty(imsi)) {
            emptyProviderLogger.warn("receiver order:{} by empty provider info ", payOrder);
        }

        payOrder.setOperatorType(PhoneUtils.getProviderId(imsi));

        String channel = payOrder.getChannel();
        //内部测试包
        if (channel.startsWith("tianzhtest")) {
            if (channel.equals("tianzhtest01")) {
                payOrder.letuPay();
            }

            if (channel.equals("tianzhtest02")) {
                payOrder.yuanlangPay();
            }

            if (channel.equals("tianzhtest03")) {
                payOrder.zhangPay();
            }

            if (channel.equals("tianzhtest04")) {
                payOrder.zhuquePay();
            }

            return;
        }

        if (channel.equals("selfpaidways")) {
            payOrder.selfPaidWays();

            return;
        }


        //获取所有开关打开的第三方支付
        List<ThPayProperty> allThPay = chargeOrderService.getAllThPay();

        if (allThPay == null || allThPay.size() == 0) {
            //记录日志，返回错误信息
            emptyThpayLogger.warn("response error because have no thpay to use by order:{} ", payOrder);

            //订单入库
            payOrder.setTianzhOrderId(Integer.toHexString(insertDataBases(payOrder)));
            HttpUtils.responseOrderError(payOrder);
            return;
        }


        int operatorType = payOrder.getOperatorType();

        //选出优势或者开通省份的支付方式
        ArrayList<ThPayProperty> usefullThPay = new ArrayList<ThPayProperty>();

        for (ThPayProperty thPayProperty : allThPay) {
            //选出优势省份的支付方式
            List<String> availableProvince = chargeOrderService.getAvailableProvince(operatorType, thPayProperty.getThPayId(), 1);

            if (availableProvince != null && availableProvince.size() != 0) {
//                for (String temp : availableProvince) {
                if (availableProvince.contains(province)) {
                    usefullThPay.add(thPayProperty);
                    continue;
                }
//                }
            }
        }

        if (usefullThPay.size() == 0) {

            for (ThPayProperty thPayProperty : allThPay) {
                //选出开通省份的支付方式
                List<String> availableProvince = chargeOrderService.getAvailableProvince(operatorType, thPayProperty.getThPayId(), 0);

                if (availableProvince != null && availableProvince.size() != 0) {
//                for (String temp : availableProvince) {
                    if (availableProvince.contains(province)) {
                        usefullThPay.add(thPayProperty);
                        continue;
                    }
//                }
                }
            }
        }

        if (usefullThPay.size() == 0) {
            //记录日志，返回错误信息
            emptyThpayLogger.warn("response error because have no usefull thpay to use by order:{} ", payOrder);

            payOrder.setTianzhOrderId(Integer.toHexString(insertDataBases(payOrder)));
            HttpUtils.responseOrderError(payOrder);
            return;
        }

        //按照支付权重选出可用支付
        ThPayProperty thPayProperty = WeightUtil.getThPayProperty(usefullThPay);

        payOrder.setThPayProperty(thPayProperty);

        switch (Integer.parseInt(thPayProperty.getThPayId())) {
            case 1:
                //乐途支付
                payOrder.letuPay();
                break;

            case 2:
                //元朗支付
                payOrder.yuanlangPay();
                break;

            case 3:
                //掌支付
                payOrder.zhangPay();
                break;

            case 4:
                //朱雀支付
                payOrder.zhuquePay();
                break;

            default:
                //默认支付方式
                payOrder.zhuquePay();

        }
    }


    private Integer insertDataBases(PayOrder payOrder) {
        ChargeOrderBean bean = new ChargeOrderBean();

        FeePoint feePoint = chargeOrderService.getFeePoint(payOrder.getProductId());

        bean.setProvinceId(AreaUtils.getProvinceId(payOrder.getProvince()));
        bean.setCityId(0);//暂时不处理
        bean.setProviderId(payOrder.getOperatorType());
        bean.setUserToken(payOrder.getUserToken());
        bean.setOrderStatus(5);
        bean.setTianzhOrderId(payOrder.getTianzhOrderId());
        bean.setProductId(payOrder.getProductId());
        bean.setProductName(feePoint.getFeePointName());
        bean.setUserOrderId(payOrder.getUserOrderId());
        bean.setAppId(payOrder.getAppId());
        bean.setAppName(feePoint.getProductName());
        bean.setUserOrderId(payOrder.getUserOrderId());
        bean.setChannelId(payOrder.getChannel());
        bean.setProductPrice(feePoint.getPrice());
        bean.setProvince(payOrder.getProvince());
        bean.setCity(payOrder.getCity());
        bean.setImei(payOrder.getImei());
        bean.setImsi(payOrder.getImsi());
        bean.setPhone(payOrder.getPhoneNum());
        bean.setModel(payOrder.getModel());
        bean.setThpayType(0);
        bean.setThpayName("无可用支付");
        bean.setCreateTime(Calendar.getInstance().getTime());

        if (!chargeOrderService.insertThpayOrder(bean)) {
            //插入数据库异常，记录日志
            thpayOrderDaoLogger.error("fail insert ChargeOrderBean:{} into databases", bean.toString());
        }

        return bean.getId();

    }
}
