package com.tianzh.cm.constant;

import org.jboss.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Author: cyc
 * Date: 12-3-18
 * Time: 下午3:21
 * Description: to write something
 */
public class SystemConstants {
    public static final String DEFAULT_ENCODE = "UTF-8";
    public static final Charset DEFAULT_CHARSET = CharsetUtil.UTF_8;
    public static final String DEFAULT_HTTP_CONTENT_TYPE = "application/json;charset=UTF-8";


    public static final Integer USERFULLTOKEN = 0;
    public static final Integer UNUSERFULLTOKEN = 1;

}

