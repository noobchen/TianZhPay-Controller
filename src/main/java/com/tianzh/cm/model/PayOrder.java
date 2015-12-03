package com.tianzh.cm.model;

import com.tianzh.cm.annotation.Send;
import com.tianzh.cm.domain.message.DomainMessage;
import com.tianzh.cm.service.model.ThPayProperty;
import com.tianzh.cm.util.event.EventUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by pig on 2015-09-10.
 */
public class PayOrder extends ClientRequestModel  {

    String userToken;
    String appId;
    String productId;
    String userOrderId;
    String channel;
    String province;
    String city;
    String imsi;
    String imei;
    String phoneNum;
    String model;
    String mac;


    @JsonIgnore
    int operatorType;

    @JsonIgnore
    String tianzhOrderId;

    @JsonIgnore
    ThPayProperty thPayProperty;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setThPayProperty(ThPayProperty thPayProperty) {
        this.thPayProperty = thPayProperty;
    }

    public String getTianzhOrderId() {
        return tianzhOrderId;
    }

    public void setTianzhOrderId(String tianzhOrderId) {
        this.tianzhOrderId = tianzhOrderId;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PayOrder{");
        sb.append("appId='").append(appId).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", userOrderId='").append(userOrderId).append('\'');
        sb.append(", channel='").append(channel).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", imsi='").append(imsi).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", phoneNum='").append(phoneNum).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", operatorType=").append(operatorType);
        sb.append(", tianzhOrderId='").append(tianzhOrderId).append('\'');
        sb.append(", userToken='").append(userToken).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public DomainMessage fireSelf() {
        DomainMessage em = new DomainMessage(this);

        EventUtils.fireEvent(em, "receivePayOrderState");

        return em;
    }


    @Send("letuPayState")
    @JsonIgnore
    public DomainMessage letuPay() {
        DomainMessage em = new DomainMessage(this);
        EventUtils.fireEvent(em, "letuPayState");
        return em;
    }

    @Send("yuanlangPayState")
    @JsonIgnore
    public DomainMessage yuanlangPay() {
        DomainMessage em = new DomainMessage(this);
        EventUtils.fireEvent(em, "yuanlangPayState");
        return em;
    }


    @Send("zhangPayState")
    @JsonIgnore
    public DomainMessage zhangPay() {
        DomainMessage em = new DomainMessage(this);
        EventUtils.fireEvent(em, "zhangPayState");
        return em;
    }

    @Send("zhuquePayState")
    @JsonIgnore
    public DomainMessage zhuquePay() {
        DomainMessage em = new DomainMessage(this);
        EventUtils.fireEvent(em, "zhuquePayState");
        return em;
    }

    @Send("selfPaidWays")
    @JsonIgnore
    public DomainMessage selfPaidWays() {
        DomainMessage em = new DomainMessage(this);
        EventUtils.fireEvent(em, "selfPaidWays");
        return em;
    }
}
