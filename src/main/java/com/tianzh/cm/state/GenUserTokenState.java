package com.tianzh.cm.state;

import com.tianzh.cm.annotation.Consumer;
import com.tianzh.cm.constant.SystemConstants;
import com.tianzh.cm.domain.message.DomainEventHandler;
import com.tianzh.cm.event.disuptor.EventDisruptor;
import com.tianzh.cm.model.UserToken;
import com.tianzh.cm.service.init.GenUserTokenService;
import com.tianzh.cm.service.model.UserTokenBean;
import com.tianzh.cm.util.http.HttpUtils;
import com.tianzh.cm.util.token.TokenUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by pig on 2015-09-28.
 */
@Consumer("genUserTokenState")
public class GenUserTokenState implements DomainEventHandler {
    Logger genUserTokenDaoLogger = LoggerFactory.getLogger("gentoken_dao");

    GenUserTokenService genUserTokenService;

    public void setGenUserTokenService(GenUserTokenService genUserTokenService) {
        this.genUserTokenService = genUserTokenService;
    }

    @Override
    public void onEvent(EventDisruptor event, boolean endOfBatch) throws Exception {
        UserToken userToken = (UserToken) event.getDomainMessage().getEventSource();
        String token = TokenUtils.generateUserToken(userToken.getAppId(), userToken.getMac(), userToken.getImei());

        HashMap<String, String> response = new HashMap<String, String>();
        response.put("statusCode", "200");
        response.put("usertoken", token);

        HttpUtils.response(userToken, response);

        //存入数据库
        UserTokenBean userTokenBean = new UserTokenBean();

        userTokenBean.setAppid(userToken.getAppId());
        userTokenBean.setChannel(userToken.getChannel());
        userTokenBean.setUserToken(token);
        userTokenBean.setCreateTime(Calendar.getInstance().getTime());

        boolean simReady = userToken.isSimReady();

        if (!simReady) {
            genUserTokenDaoLogger.error("Usertoken:{} sim is not ready !");
            userTokenBean.setStatus(SystemConstants.UNUSERFULLTOKEN);
        } else {
            userTokenBean.setStatus(SystemConstants.USERFULLTOKEN);
        }


        try {
            genUserTokenService.insertUserToken(userTokenBean);

        } catch (Exception e) {
            e.printStackTrace();
            genUserTokenDaoLogger.error("insert Usertoken:{} fail exception:{}", userTokenBean, ExceptionUtils.getMessage(e));
        }


    }
}
