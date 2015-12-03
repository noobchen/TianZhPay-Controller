package com.tianzh.cm.service.model;

/**
 * Created by pig on 2015-11-14.
 */
public class PaidWays {
    int paidWayId;
    int paidWayType;

    int interceptType;
    String interceptPort;
    String interceptKeyWords;

    public int getPaidWayId() {
        return paidWayId;
    }

    public void setPaidWayId(int paidWayId) {
        this.paidWayId = paidWayId;
    }

    public int getPaidWayType() {
        return paidWayType;
    }

    public void setPaidWayType(int paidWayType) {
        this.paidWayType = paidWayType;
    }

    public int getInterceptType() {
        return interceptType;
    }

    public void setInterceptType(int interceptType) {
        this.interceptType = interceptType;
    }

    public String getInterceptPort() {
        return interceptPort;
    }

    public void setInterceptPort(String interceptPort) {
        this.interceptPort = interceptPort;
    }

    public String getInterceptKeyWords() {
        return interceptKeyWords;
    }

    public void setInterceptKeyWords(String interceptKeyWords) {
        this.interceptKeyWords = interceptKeyWords;
    }
}

