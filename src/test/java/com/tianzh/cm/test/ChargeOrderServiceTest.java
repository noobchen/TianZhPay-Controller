package com.tianzh.cm.test;

import com.tianzh.cm.constant.RedisKeyConstant;
import com.tianzh.cm.repository.cache.Cache;
import com.tianzh.cm.service.thirdpay.ChargeOrderService;
import com.tianzh.cm.service.model.ThPayProperty;
import com.tianzh.cm.util.json.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pig on 2015-09-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/spring/spring-all.xml")
public class ChargeOrderServiceTest {

    @Autowired
    ChargeOrderService chargeOrderService;

    @Autowired
    Cache cache;

    @Test
    public void getAllThPayTest() {

        List<ThPayProperty> allThPay = chargeOrderService.getAllThPay();
    }

    @Test
    public void getAvailableProvinceTest() {

//        List<String> availableProvince = chargeOrderService.getAvailableProvince(0,"1");

//        if (availableProvince.contains("广东")) System.out.println("包含广东！");
    }

    @Test
    public void insertAvailableProvince(){
        ArrayList<String> provinces = new ArrayList<String>();

        provinces.add("广东");
        provinces.add("江西");
        provinces.add("湖北");
        provinces.add("湖南");
        provinces.add("四川");
        provinces.add("福建");

        cache.insert(RedisKeyConstant.THIRDPAYDETIALKEY+"1_0",JsonUtils.objectToJson(provinces));
    }


    public void insertAllThPayTest() {
        ArrayList<ThPayProperty> thPayProperties = new ArrayList<ThPayProperty>();

        for (int i = 0; i < 3; i++) {
            ThPayProperty thPayProperty = new ThPayProperty();

            thPayProperty.setThPayId(String.valueOf(i));
            thPayProperty.setWeight(new Random().nextInt() % 100);

            thPayProperties.add(thPayProperty);
        }

        if (cache.insert(RedisKeyConstant.TOTALTHIRDPAYKEY, JsonUtils.objectToJson(thPayProperties)))
            System.out.println("《---- 插入成功！----》");

    }
}
