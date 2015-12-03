package com.tianzh.cm.service.model;

/**
 * Created by pig on 2015-11-14.
 */
public class CombinationPaidMsg extends PaidWays {

    String delay;
    String firstPort;
    String firstMsg;
    String secondPort;
    String secondMsg;

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getFirstPort() {
        return firstPort;
    }

    public void setFirstPort(String firstPort) {
        this.firstPort = firstPort;
    }

    public String getFirstMsg() {
        return firstMsg;
    }

    public void setFirstMsg(String firstMsg) {
        this.firstMsg = firstMsg;
    }

    public String getSecondPort() {
        return secondPort;
    }

    public void setSecondPort(String secondPort) {
        this.secondPort = secondPort;
    }

    public String getSecondMsg() {
        return secondMsg;
    }

    public void setSecondMsg(String secondMsg) {
        this.secondMsg = secondMsg;
    }
}
