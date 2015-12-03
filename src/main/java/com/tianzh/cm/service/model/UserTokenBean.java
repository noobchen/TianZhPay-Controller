package com.tianzh.cm.service.model;

import java.util.Date;

/**
 * Created by pig on 2015-09-28.
 */
public class UserTokenBean {
    String appid;
    String mac;
    String imei;
    String channel;
    String userToken;
    Integer status;
    Date createTime;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTokenBean{");
        sb.append("appid='").append(appid).append('\'');
        sb.append(", mac='").append(mac).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", channel='").append(channel).append('\'');
        sb.append(", userToken='").append(userToken).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
