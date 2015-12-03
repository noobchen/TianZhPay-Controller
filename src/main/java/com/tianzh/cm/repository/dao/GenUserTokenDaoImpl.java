package com.tianzh.cm.repository.dao;

import com.tianzh.cm.service.model.UserTokenBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * Created by pig on 2015-09-28.
 */
public class GenUserTokenDaoImpl extends SqlSessionTemplate implements GenUserTokenDao {

    public GenUserTokenDaoImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public void insertUserToken(UserTokenBean userTokenBean) {
        insert("usertoken.insertUserToken", userTokenBean);
    }
}
