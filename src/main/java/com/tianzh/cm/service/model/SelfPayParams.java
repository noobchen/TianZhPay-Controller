package com.tianzh.cm.service.model;

import java.util.ArrayList;

/**
 * Created by pig on 2015-11-14.
 */
public class SelfPayParams extends ResponseParams{
    private ArrayList<PaidWays> paidWays;
    private int delay;
    private int dialogType;
    private String productPrompt = "";
    private String thpayType = "0";

    public String getThpayType() {
        return thpayType;
    }

    public void setThpayType(String thpayType) {
        this.thpayType = thpayType;
    }

    public ArrayList<PaidWays> getPaidWays() {
        return paidWays;
    }

    public void setPaidWays(ArrayList<PaidWays> paidWays) {
        this.paidWays = paidWays;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDialogType() {
        return dialogType;
    }

    public void setDialogType(int dialogType) {
        this.dialogType = dialogType;
    }

    public String getProductPrompt() {
        return productPrompt;
    }

    public void setProductPrompt(String productPrompt) {
        this.productPrompt = productPrompt;
    }
}
