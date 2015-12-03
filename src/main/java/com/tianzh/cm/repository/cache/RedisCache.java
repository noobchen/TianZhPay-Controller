package com.tianzh.cm.repository.cache;

import com.tianzh.cm.repository.cache.Cache;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: cyc
 * Date: 12-3-22
 * Time: 上午9:50
 * Description: redis cache class
 */
public class RedisCache implements Cache {
    private final Logger logger = LoggerFactory.getLogger("redis_exception");

    private final String DEFAULT_HOST = "120.24.1.156";
    private final int DEFAULT_PORT = 6379;
    private JedisPool pool;
    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private int timeout = 5000;
    private int maxActive = 100;
    private int maxIdle = 20;
    private int maxWait = 1000;
    private boolean testOnBorrow = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWait(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        pool = new JedisPool(config, host, port, timeout,"Sztz12345");
    }

    @Override
    public void destroy() {
        pool.destroy();
    }

    @Override
    public String query(String key) {
        Jedis jedis = null;

        try {
            jedis = pool.getResource();

            return jedis.get(key);
        } catch (Exception e) {
            logger.error("redis query:{} exception:{}", key, ExceptionUtils.getStackTrace(e));
            return "";
        } finally {
            pool.returnResource(jedis);
        }
    }

    @Override
    public boolean insert(String key, String json) {
        Jedis jedis = null;

        try {
            jedis = pool.getResource();
            String response = jedis.set(key, json);

            if (response.equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("redis insert:{} value:{} exception:{}", new Object[]{key, json, ExceptionUtils.getStackTrace(e)});
            return false;
        } finally {
            pool.returnResource(jedis);
        }
    }

    @Override
    public boolean del(String key) {
        Jedis jedis = null;

        try {
            jedis = pool.getResource();
            long delNum = jedis.del(key);

            if (delNum > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("redis del:{} exception:{}", new Object[]{key, ExceptionUtils.getStackTrace(e)});

            return false;
        } finally {
            pool.returnResource(jedis);
        }
    }


}
