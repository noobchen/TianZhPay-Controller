package com.tianzh.cm.service.model;

/**
 * Created by pig on 2015-11-14.
 */
public class NormalPaidMsg extends PaidWays {

    String port;
    String msg;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
