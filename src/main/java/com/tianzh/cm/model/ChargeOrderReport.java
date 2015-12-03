package com.tianzh.cm.model;

import com.tianzh.cm.domain.message.DomainMessage;
import com.tianzh.cm.util.event.EventUtils;

/**
 * Created by pig on 2015-09-10.
 */
public class ChargeOrderReport extends ClientRequestModel {
    Integer id;
    String tianzhOrderId;
    String statusCode;
    String discribe;
    String discribeCode;
    String reportType;
    String paidWaysId;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getPaidWaysId() {
        return paidWaysId;
    }

    public void setPaidWaysId(String paidWaysId) {
        this.paidWaysId = paidWaysId;
    }

    public Integer getId() {
        return Integer.parseInt(tianzhOrderId,16);
    }

    public String getTianzhOrderId() {
        return tianzhOrderId;
    }

    public void setTianzhOrderId(String tianzhOrderId) {
        this.tianzhOrderId = tianzhOrderId;
    }

    public String getStatusCode() {
        return statusCode.equals("200") ? "2" : statusCode.equals("300") ? "3" : "5";
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getDiscribeCode() {
        return discribeCode;
    }

    public void setDiscribeCode(String discribeCode) {
        this.discribeCode = discribeCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChargeOrderReport{");
        sb.append("id=").append(id);
        sb.append(", tianzhOrderId='").append(tianzhOrderId).append('\'');
        sb.append(", statusCode='").append(statusCode).append('\'');
        sb.append(", discribe='").append(discribe).append('\'');
        sb.append(", discribeCode='").append(discribeCode).append('\'');
        sb.append(", reportType='").append(reportType).append('\'');
        sb.append(", paidWaysId='").append(paidWaysId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public DomainMessage fireSelf() {
        DomainMessage em = new DomainMessage(this);

        EventUtils.fireEvent(em, "chargeOrderReportState");

        return em;
    }
}
