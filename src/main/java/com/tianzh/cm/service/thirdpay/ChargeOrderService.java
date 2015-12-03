package com.tianzh.cm.service.thirdpay;

import com.tianzh.cm.model.ChargeOrderReport;
import com.tianzh.cm.service.model.*;

import java.util.List;

/**
 * Created by pig on 2015-09-11.
 */
public interface ChargeOrderService {
    List<ThPayProperty> getAllThPay();

    List<String> getAvailableProvince(int operatorId, String ThPayId, int provinceType);

    LTPayParams getLTPayParams(String productId);

    YLPayParams getYLPayParams(String productId);

    ZhangPayParams getZhangPayParams(String productId);

    ZhuquePayParams getZhuquePayParams(String productId);

    boolean insertThpayOrder(ChargeOrderBean chargeOrderBean);

    boolean updateThpayOrder(ChargeOrderReport report);

    boolean updateThpayOrder(ChargeOrderBean bean);

    FeePoint getFeePoint(String productId);
}
