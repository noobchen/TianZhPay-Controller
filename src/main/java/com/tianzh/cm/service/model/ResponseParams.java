package com.tianzh.cm.service.model;

/**
 * Created by pig on 2015-11-14.
 */
public class ResponseParams {
    String productId;
    String userOrderId;
    String tianzhOrderId;
    String statusCode;
    String userToken;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getTianzhOrderId() {
        return tianzhOrderId;
    }

    public void setTianzhOrderId(String tianzhOrderId) {
        this.tianzhOrderId = tianzhOrderId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
