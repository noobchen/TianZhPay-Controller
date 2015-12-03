package com.tianzh.cm.model;

import com.tianzh.cm.domain.message.DomainMessage;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: cyc
 * Date: 12-3-18
 * Time: 下午1:11
 * Description: to write something
 */
public abstract class ClientRequestModel {
    @JsonIgnore
    public final String MESSAGE_EVENT_KEY = "e";
    @JsonIgnore
    public final String HTTP_REQUEST_KEY = "r";
    @JsonIgnore
    public static final String HTTP_PHONE_KEY = "p";
    @JsonIgnore
    public final String IP_KEY = "i";


    @JsonIgnore
    private Map<String, Object> properties = new HashMap<String, Object>();

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public String getClientIp() {
        return ((String) getProperty(IP_KEY));
    }

    public abstract DomainMessage fireSelf();
}
