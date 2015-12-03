package com.tianzh.cm.repository.dao;

import com.tianzh.cm.model.ChargeOrderReport;
import com.tianzh.cm.service.model.*;

import java.util.List;

/**
 * Created by pig on 2015-09-12.
 */
public interface ThPayOrderDao {

    boolean insertThPayOrder(ChargeOrderBean chargeOrderBean);

    boolean updateThpayOrder(ChargeOrderReport report);

    boolean updateThpayOrder(ChargeOrderBean bean);

    FeePoint findFeePoint(String feepointId);

    FeePoint findLTPayParams(String feepointId);

    FeePoint findYlPayParams(String feepointId);

    FeePoint findZhPayParams(String feepointId);

    List<ThPayProperty> getAllThPay();

    List<String> getAvailableProvince(int operatorId, String ThPayId, int provinceType);

}
