package com.tianzh.cm.test;

import com.tianzh.cm.repository.cache.Cache;
import com.tianzh.cm.repository.cache.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pig on 2015-09-11.
 */

public class RedisTest {

    @Test
    public void testSet() {
        Cache cache = new RedisCache();

        cache.init();



        cache.insert("aaaa", "bbbb");
    }

    @Test
    public void testQuery() {
        Cache cache = new RedisCache();

        cache.init();
        System.out.println(cache.query("aaaa"));
    }

    @Test
    public void testDel() {
        Cache cache = new RedisCache();

        cache.init();
        System.out.println(cache.del("aaaa"));
        cache.destroy();
    }

}
