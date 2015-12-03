package com.tianzh.cm.service.init;

import com.tianzh.cm.service.model.UserTokenBean;

/**
 * Created by pig on 2015-09-28.
 */
public interface GenUserTokenService {
    //usertoken入库
    void insertUserToken(UserTokenBean userTokenBean) throws Exception;
}
