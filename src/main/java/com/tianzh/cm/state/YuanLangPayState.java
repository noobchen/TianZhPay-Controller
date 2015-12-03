package com.tianzh.cm.state;

import com.tianzh.cm.annotation.Consumer;
import com.tianzh.cm.domain.message.DomainEventHandler;
import com.tianzh.cm.event.disuptor.EventDisruptor;
import com.tianzh.cm.model.PayOrder;
import com.tianzh.cm.service.thirdpay.ChargeOrderService;
import com.tianzh.cm.service.model.ChargeOrderBean;
import com.tianzh.cm.service.model.YLPayParams;
import com.tianzh.cm.util.area.AreaUtils;
import com.tianzh.cm.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;


/**
 * Created by pig on 2015-09-10.
 */
@Consumer("yuanlangPayState")
public class YuanLangPayState implements DomainEventHandler {
    Logger yuanlangpayBiLogger = LoggerFactory.getLogger("yuanlangpay_bi");
    Logger thpayOrderDaoLogger = LoggerFactory.getLogger("thpayorder_dao");


    ChargeOrderService chargeOrderService;

    public void setChargeOrderService(ChargeOrderService chargeOrderService) {
        this.chargeOrderService = chargeOrderService;
    }

    @Override
    public void onEvent(EventDisruptor event, boolean endOfBatch) throws Exception {
        PayOrder payOrder = (PayOrder) event.getDomainMessage().getEventSource();

        YLPayParams ylPayParams = chargeOrderService.getYLPayParams(payOrder.getProductId());

        if (ylPayParams == null) {
            yuanlangpayBiLogger.warn("redis donot exits ylPayParams response error by PayOrder:{}", payOrder);
            HttpUtils.responseOrderError(payOrder);
            return;
        }

        ChargeOrderBean bean = new ChargeOrderBean();

        //订单入库
        bean.setProvinceId(AreaUtils.getProvinceId(payOrder.getProvince()));
        bean.setCityId(0);//暂时不处理
        bean.setProviderId(payOrder.getOperatorType());
        bean.setOrderStatus(4);
        bean.setUserToken(payOrder.getUserToken());
        bean.setTianzhOrderId(payOrder.getTianzhOrderId());
        bean.setProductId(payOrder.getProductId());
        bean.setProductName(ylPayParams.getGoods_name());
        bean.setUserOrderId(payOrder.getUserOrderId());
        bean.setAppId(payOrder.getAppId());
        bean.setAppName(ylPayParams.getAppName());
        bean.setUserOrderId(payOrder.getUserOrderId());
        bean.setChannelId(payOrder.getChannel());
        bean.setProductPrice(Integer.parseInt(ylPayParams.getUnit_price()));
        bean.setProvince(payOrder.getProvince());
        bean.setCity(payOrder.getCity());
        bean.setImei(payOrder.getImei());
        bean.setImsi(payOrder.getImsi());
        bean.setPhone(payOrder.getPhoneNum());
        bean.setModel(payOrder.getModel());
        bean.setThpayType(2);
        bean.setThpayName("元朗支付");
        bean.setCreateTime(Calendar.getInstance().getTime());

        if (!chargeOrderService.insertThpayOrder(bean)) {
            //插入数据库异常，记录日志
            thpayOrderDaoLogger.error("fail insert ChargeOrderBean:{} into databases", bean.toString());
        }

        ylPayParams.setUserToken(payOrder.getUserToken());
        ylPayParams.setTianzhOrderId(Integer.toHexString(bean.getId()));
        ylPayParams.setUser_order_id(payOrder.getUserOrderId());
        ylPayParams.setUserOrderId(payOrder.getUserOrderId());
        ylPayParams.setProductId(payOrder.getProductId());
        ylPayParams.setStatusCode("200");

        if (!HttpUtils.response(payOrder, ylPayParams)) {
            yuanlangpayBiLogger.warn(" response ylPayParams:{} fail by PayOrder:{}", ylPayParams, payOrder);
            bean.setOrderStatus(5);
            chargeOrderService.updateThpayOrder(bean);
        }
    }

}
