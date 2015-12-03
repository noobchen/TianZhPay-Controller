package com.tianzh.cm.service.thirdpay;

import com.tianzh.cm.annotation.Service;
import com.tianzh.cm.constant.RedisKeyConstant;
import com.tianzh.cm.model.ChargeOrderReport;
import com.tianzh.cm.repository.cache.Cache;
import com.tianzh.cm.repository.dao.ThPayOrderDao;
import com.tianzh.cm.service.model.*;
import com.tianzh.cm.util.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pig on 2015-09-11.
 */
@Service
public class ChargeOrderServiceImpl implements ChargeOrderService {

    Cache cache;


    ThPayOrderDao thPayOrderDao;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public void setThPayOrderDao(ThPayOrderDao thPayOrderDao) {
        this.thPayOrderDao = thPayOrderDao;
    }

    @Override
    public List<ThPayProperty> getAllThPay() {

        String json = cache.query(RedisKeyConstant.TOTALTHIRDPAYKEY);

        if (StringUtils.isEmpty(json)) {
            List<ThPayProperty> allThPay = thPayOrderDao.getAllThPay();

            cache.insert(RedisKeyConstant.TOTALTHIRDPAYKEY, JsonUtils.objectToJson(allThPay));
            return allThPay;
        } else {
            return JsonUtils.jsonToObject(json, new TypeReference<ArrayList<ThPayProperty>>() {
            });
        }
    }

    @Override
    public List<String> getAvailableProvince(int operatorId, String ThPayId, int provinceType) {
        String json = cache.query(RedisKeyConstant.THIRDPAYDETIALKEY + ThPayId + "_" + operatorId + "_" + provinceType);

        if (StringUtils.isEmpty(json)) {
            List<String> availableProvince = thPayOrderDao.getAvailableProvince(operatorId, ThPayId, provinceType);

            cache.insert(RedisKeyConstant.THIRDPAYDETIALKEY + ThPayId + "_" + operatorId + "_" + provinceType, JsonUtils.objectToJson(availableProvince));
            return availableProvince;
        } else {
            return JsonUtils.jsonToObject(json, new TypeReference<ArrayList<String>>() {
            });
        }
    }

    @Override
    public LTPayParams getLTPayParams(String productId) {
        FeePoint feePoint = getFeePoint(productId);

        if (feePoint == null) return null;

        String json = cache.query(RedisKeyConstant.PRODUCTDETIALKEY_ + productId + "_" + "1");

        LTPayParams ltPayParams = new LTPayParams();

        if (StringUtils.isEmpty(json)) {
            FeePoint ltFeePoint = thPayOrderDao.findLTPayParams(productId);

            String toJson = JsonUtils.objectToJson(ltFeePoint);
            cache.insert(RedisKeyConstant.PRODUCTDETIALKEY_ + productId + "_" + "1", toJson);

            ltPayParams.setAppName(feePoint.getProductName());
            ltPayParams.setProductName(feePoint.getFeePointName());
            ltPayParams.setPrice(ltFeePoint.getLetuPrice());
            ltPayParams.setOrderDesc(ltFeePoint.getLetuFeeDesc());
            ltPayParams.setSdkChannelId(ltFeePoint.getLetuSdkChannelId());
            ltPayParams.setPayType(String.valueOf(ltFeePoint.getLetuPayType()));
            ltPayParams.setGameType(String.valueOf(ltFeePoint.getLetuGameType()));
            ltPayParams.setShowUIKey(ltFeePoint.getLetuShowUIKey());
            ltPayParams.setMerchantKey(ltFeePoint.getLetuMerchantKey());
            ltPayParams.setPayPointNum(String.valueOf(ltFeePoint.getLetuPayPointNum()));

            return ltPayParams;
        } else {

            FeePoint ltFeePoint = JsonUtils.jsonToObject(json, new TypeReference<FeePoint>() {
            });

            ltPayParams.setAppName(feePoint.getProductName());
            ltPayParams.setPrice(ltFeePoint.getLetuPrice());
            ltPayParams.setProductName(feePoint.getFeePointName());
            ltPayParams.setOrderDesc(ltFeePoint.getLetuFeeDesc());
            ltPayParams.setSdkChannelId(ltFeePoint.getLetuSdkChannelId());
            ltPayParams.setPayType(String.valueOf(ltFeePoint.getLetuPayType()));
            ltPayParams.setGameType(String.valueOf(ltFeePoint.getLetuGameType()));
            ltPayParams.setShowUIKey(ltFeePoint.getLetuShowUIKey());
            ltPayParams.setMerchantKey(ltFeePoint.getLetuMerchantKey());
            ltPayParams.setPayPointNum(String.valueOf(ltFeePoint.getLetuPayPointNum()));

            return ltPayParams;
        }
    }

    @Override
    public YLPayParams getYLPayParams(String productId) {
        FeePoint feePoint = getFeePoint(productId);

        if (feePoint == null) return null;

        String json = cache.query(RedisKeyConstant.PRODUCTDETIALKEY_ + productId + "_" + "2");

        YLPayParams ylPayParams = new YLPayParams();

        if (StringUtils.isEmpty(json)) {
            FeePoint ylFeePoint = thPayOrderDao.findYlPayParams(productId);

            String toJson = JsonUtils.objectToJson(ylFeePoint);
            cache.insert(RedisKeyConstant.PRODUCTDETIALKEY_ + productId + "_" + "2", toJson);

            ylPayParams.setAppName(feePoint.getProductName());
            ylPayParams.setGoods_name(feePoint.getFeePointName());
            ylPayParams.setUnit_price(ylFeePoint.getyLPrice());
            ylPayParams.setGoods_id(ylFeePoint.getyLGoodsId());
            ylPayParams.setIs_online(String.valueOf(ylFeePoint.getyLIsOnline() == 1));

            return ylPayParams;

        } else {

            FeePoint ylfeePoint = JsonUtils.jsonToObject(json, new TypeReference<FeePoint>() {
            });


            ylPayParams.setAppName(feePoint.getProductName());
            ylPayParams.setGoods_name(feePoint.getFeePointName());
            ylPayParams.setUnit_price(ylfeePoint.getyLPrice());
            ylPayParams.setGoods_id(ylfeePoint.getyLGoodsId());
            ylPayParams.setIs_online(String.valueOf(ylfeePoint.getyLIsOnline() == 1));

            return ylPayParams;
        }
    }

    @Override
    public ZhangPayParams getZhangPayParams(String productId) {
        FeePoint feePoint = getFeePoint(productId);

        if (feePoint == null) return null;

        String json = cache.query(RedisKeyConstant.PRODUCTDETIALKEY_ + productId + "_" + "3");

        ZhangPayParams zhangPayParams = new ZhangPayParams();

        if (StringUtils.isEmpty(json)) {
            FeePoint zhFeePoint = thPayOrderDao.findZhPayParams(productId);

            String toJson = JsonUtils.objectToJson(zhFeePoint);
            cache.insert(RedisKeyConstant.PRODUCTDETIALKEY_ + productId + "_" + "3", toJson);

            zhangPayParams.setAppName(feePoint.getProductName());
            zhangPayParams.setPriciepointName(feePoint.getFeePointName());
            zhangPayParams.setMoney(zhFeePoint.getZhPrice());
            zhangPayParams.setPriciepointDec(zhFeePoint.getZhFeeDesc());
            zhangPayParams.setKey(zhFeePoint.getZhangKey());
            zhangPayParams.setPriciePointId(zhFeePoint.getZhangPricePointId());
            zhangPayParams.setAppVersion(zhFeePoint.getZhangAppVersion());

            return zhangPayParams;
        } else {

            FeePoint zhFeePoint = JsonUtils.jsonToObject(json, new TypeReference<FeePoint>() {
            });


            zhangPayParams.setAppName(feePoint.getProductName());
            zhangPayParams.setMoney(zhFeePoint.getZhPrice());
            zhangPayParams.setPriciepointName(feePoint.getFeePointName());
            zhangPayParams.setPriciepointDec(zhFeePoint.getZhFeeDesc());
            zhangPayParams.setKey(zhFeePoint.getZhangKey());
            zhangPayParams.setPriciePointId(zhFeePoint.getZhangPricePointId());
            zhangPayParams.setAppVersion(zhFeePoint.getZhangAppVersion());

            return zhangPayParams;
        }
    }

    @Override
    public ZhuquePayParams getZhuquePayParams(String productId) {
        FeePoint feePoint = getFeePoint(productId);

        if (feePoint == null) return null;

        ZhuquePayParams zhuquePayParams = new ZhuquePayParams();

        zhuquePayParams.setAppName(feePoint.getProductName());
        zhuquePayParams.setProductName(feePoint.getFeePointName());
        zhuquePayParams.setPrice(String.valueOf(feePoint.getPrice()));

        return zhuquePayParams;
    }

    @Override
    public boolean insertThpayOrder(ChargeOrderBean chargeOrderBean) {
        return thPayOrderDao.insertThPayOrder(chargeOrderBean);
    }

    @Override
    public boolean updateThpayOrder(ChargeOrderReport report) {
        return thPayOrderDao.updateThpayOrder(report);

    }

    @Override
    public boolean updateThpayOrder(ChargeOrderBean bean) {
        return thPayOrderDao.updateThpayOrder(bean);
    }

    @Override
    public FeePoint getFeePoint(String productId) {

        String json = cache.query(RedisKeyConstant.PRODUCTDETIALKEY_ + productId);

        if (StringUtils.isEmpty(json)) {
            FeePoint feePoint = thPayOrderDao.findFeePoint(productId);

            String toJson = JsonUtils.objectToJson(feePoint);
            cache.insert(RedisKeyConstant.PRODUCTDETIALKEY_ + productId, toJson);

            return feePoint;
        } else {
            return JsonUtils.jsonToObject(json, new TypeReference<FeePoint>() {
            });
        }
    }
}
