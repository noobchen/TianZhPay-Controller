package com.tianzh.cm.service.init;

import com.tianzh.cm.repository.dao.GenUserTokenDao;
import com.tianzh.cm.service.model.UserTokenBean;

/**
 * Created by pig on 2015-09-28.
 */
public class GenUserTokenServiceImpl implements GenUserTokenService {
    GenUserTokenDao genUserTokenDao;

    public void setGenUserTokenDao(GenUserTokenDao genUserTokenDao) {
        this.genUserTokenDao = genUserTokenDao;
    }

    @Override
    public void insertUserToken(UserTokenBean userTokenBean) throws Exception {
        genUserTokenDao.insertUserToken(userTokenBean);
    }
}
