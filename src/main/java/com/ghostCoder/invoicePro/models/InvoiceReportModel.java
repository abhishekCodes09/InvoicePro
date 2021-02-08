package com.ghostCoder.invoicePro.models;

import java.sql.Date;

public class InvoiceReportModel {
    private int lrNo;
    private String lrDate;
    private String vehicleNo;
    private String weight;
    private String noOfPackage;
    private String invoiceNo;
    private int amount;
    private String rate;
    private String paisa;

    public InvoiceReportModel(int lrNo, String lrDate, String vehicleNo, String weight, String noOfPackage,
                              String invoiceNo, int amount, String rate, String paisa) {
        this.lrNo = lrNo;
        this.lrDate = lrDate;
        this.vehicleNo = vehicleNo;
        this.weight = weight;
        this.noOfPackage = noOfPackage;
        this.invoiceNo = invoiceNo;
        this.amount = amount;
        this.rate = rate;
        this.paisa = paisa;
    }

    public int getLrNo() {
        return lrNo;
    }

    public void setLrNo(int lrNo) {
        this.lrNo = lrNo;
    }

    public String getLrDate() {
        return lrDate;
    }

    public void setLrDate(String lrDate) {
        this.lrDate = lrDate;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNoOfPackage() {
        return noOfPackage;
    }

    public void setNoOfPackage(String noOfPackage) {
        this.noOfPackage = noOfPackage;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPaisa() {
        return paisa;
    }

    public void setPaisa(String paisa) {
        this.paisa = paisa;
    }
}
