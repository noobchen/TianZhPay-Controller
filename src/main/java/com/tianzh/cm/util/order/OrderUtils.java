package com.tianzh.cm.util.order;

import java.util.UUID;

/**
 * Author: cyc
 * Date: 12-3-20
 * Time: 上午11:25
 * Description: to write something
 */
public class OrderUtils {
    public static String getPayOrderId() {
        return UUID.randomUUID().toString();
    }

}
