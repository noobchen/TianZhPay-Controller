package com.tianzh.cm.repository.cache;

import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * Author: cyc
 * Date: 12-3-22
 * Time: 上午9:47
 * Description: to write something
 */
public interface Cache {
    void init();

    void destroy();

    String query(String key);

    boolean insert(String key,String json);

    boolean del(String key);
}
