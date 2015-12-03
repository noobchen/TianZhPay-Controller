package com.tianzh.cm.service.model;

/**
 * Created by pig on 2015-11-14.
 */
public class ConfirmPaidMsg extends NormalPaidMsg {
    String interceptPreKeyWords;
    String interceptBackKeyWords;
    String confirmPort;
    String confirmMsgDelay;


    public String getInterceptPreKeyWords() {
        return interceptPreKeyWords;
    }

    public void setInterceptPreKeyWords(String interceptPreKeyWords) {
        this.interceptPreKeyWords = interceptPreKeyWords;
    }

    public String getInterceptBackKeyWords() {
        return interceptBackKeyWords;
    }

    public void setInterceptBackKeyWords(String interceptBackKeyWords) {
        this.interceptBackKeyWords = interceptBackKeyWords;
    }

    public String getConfirmPort() {
        return confirmPort;
    }

    public void setConfirmPort(String confirmPort) {
        this.confirmPort = confirmPort;
    }

    public String getConfirmMsgDelay() {
        return confirmMsgDelay;
    }

    public void setConfirmMsgDelay(String confirmMsgDelay) {
        this.confirmMsgDelay = confirmMsgDelay;
    }
}
