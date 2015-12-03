package com.tianzh.cm.state;

import com.tianzh.cm.annotation.Consumer;
import com.tianzh.cm.domain.message.DomainEventHandler;
import com.tianzh.cm.event.disuptor.EventDisruptor;
import com.tianzh.cm.model.ChargeOrderReport;
import com.tianzh.cm.service.thirdpay.ChargeOrderService;
import com.tianzh.cm.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


/**
 * Created by pig on 2015-09-10.
 */
@Consumer("chargeOrderReportState")
public class ChargeOrderReportState implements DomainEventHandler {
    Logger thpayOrderDaoLogger = LoggerFactory.getLogger("thpayorder_dao");

    ChargeOrderService chargeOrderService;

    public void setChargeOrderService(ChargeOrderService chargeOrderService) {
        this.chargeOrderService = chargeOrderService;
    }

    @Override
    public void onEvent(EventDisruptor event, boolean endOfBatch) throws Exception {
        ChargeOrderReport chargeOrderReport = (ChargeOrderReport) event.getDomainMessage().getEventSource();

        HashMap<String, String> response = new HashMap<String, String>();

        response.put("statusCode", "200");
        response.put("statusDesc", "成功！");

        HttpUtils.response(chargeOrderReport, response);

        if (!chargeOrderService.updateThpayOrder(chargeOrderReport)) {
            //更新订单状态失败，记录日志
            thpayOrderDaoLogger.error("fail update thpay_order chargeOrderReport:{}", chargeOrderReport);
        }

    }


}
