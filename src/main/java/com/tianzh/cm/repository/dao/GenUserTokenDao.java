package com.tianzh.cm.repository.dao;

import com.tianzh.cm.service.model.UserTokenBean;

/**
 * Created by pig on 2015-09-28.
 */
public interface GenUserTokenDao {
    void insertUserToken(UserTokenBean userTokenBean);
}
