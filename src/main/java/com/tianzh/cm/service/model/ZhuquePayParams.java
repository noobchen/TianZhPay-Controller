package com.tianzh.cm.service.model;

/**
 * Created by pig on 2015-09-11.
 */
public class ZhuquePayParams extends ResponseParams{

    String userToken;
    String price;
    String tianzhOrderId;
    String statusCode;
    String thpayType = "4";
    String productId;
    String productName;
    String appName;
    String userOrderId;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getThpayType() {
        return thpayType;
    }

    public void setThpayType(String thpayType) {
        this.thpayType = thpayType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ZhuquePayParams{");
        sb.append("userToken='").append(userToken).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", tianzhOrderId='").append(tianzhOrderId).append('\'');
        sb.append(", statusCode='").append(statusCode).append('\'');
        sb.append(", thpayType='").append(thpayType).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", appName='").append(appName).append('\'');
        sb.append(", userOrderId='").append(userOrderId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
