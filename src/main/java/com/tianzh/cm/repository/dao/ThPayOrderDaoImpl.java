package com.tianzh.cm.repository.dao;

import com.tianzh.cm.annotation.Service;
import com.tianzh.cm.model.ChargeOrderReport;
import com.tianzh.cm.service.model.ChargeOrderBean;
import com.tianzh.cm.service.model.FeePoint;
import com.tianzh.cm.service.model.ThPayProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pig on 2015-09-12.
 */
@Service
public class ThPayOrderDaoImpl extends SqlSessionTemplate implements ThPayOrderDao {


    public ThPayOrderDaoImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public boolean insertThPayOrder(ChargeOrderBean chargeOrderBean) {
        return insert("charge-order.insertThpayOrder", chargeOrderBean) > 0;
    }

    @Override
    public boolean updateThpayOrder(ChargeOrderReport report) {
        return update("charge-order.updateThpayOrder", report) > 0;
    }

    @Override
    public boolean updateThpayOrder(ChargeOrderBean bean) {
        return update("charge-order.updateThpayOrder2", bean) > 0;
    }

    @Override
    public FeePoint findFeePoint(String feepointId) {
        return (FeePoint) selectOne("charge-order.findFeePoint", feepointId);
    }

    @Override
    public FeePoint findLTPayParams(String feepointId) {
        return (FeePoint) selectOne("charge-order.findLTPayParams", feepointId);
    }

    @Override
    public FeePoint findYlPayParams(String feepointId) {
        return (FeePoint) selectOne("charge-order.findYlPayParams", feepointId);
    }

    @Override
    public FeePoint findZhPayParams(String feepointId) {
        return (FeePoint) selectOne("charge-order.findZhPayParams", feepointId);
    }

    @Override
    public List<ThPayProperty> getAllThPay() {

        return (List<ThPayProperty>) selectList("charge-order.getAllThPay");
    }

    @Override
    public List<String> getAvailableProvince(int operatorId, String ThPayId, int provinceType) {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("operatorId", operatorId);
        params.put("ThPayId", ThPayId);
        params.put("provinceType", provinceType);
        return (List<String>) selectList("charge-order.getAvailableProvince", params);
    }
}
