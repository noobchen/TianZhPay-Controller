package com.tianzh.cm.model;

import com.tianzh.cm.domain.message.DomainMessage;
import com.tianzh.cm.util.event.EventUtils;

/**
 * Created by pig on 2015-09-28.
 */
public class UserToken extends ClientRequestModel {
    String appId;
    String mac;
    String imei;
    String channel;
    boolean simReady;

    public boolean isSimReady() {
        return simReady;
    }

    public void setSimReady(boolean simReady) {
        this.simReady = simReady;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserToken{");
        sb.append("appId='").append(appId).append('\'');
        sb.append(", mac='").append(mac).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", channel='").append(channel).append('\'');
        sb.append(", simReady=").append(simReady);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public DomainMessage fireSelf() {
        DomainMessage domainMessage = new DomainMessage(this);
        EventUtils.fireEvent(domainMessage, "genUserTokenState");
        return domainMessage;
    }
}
