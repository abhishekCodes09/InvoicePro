package com.ghostCoder.invoicePro.models;

import java.sql.Date;

public class InvoiceFModel {
    private String billNo;
    private int lrNo;
    private Date billDate;
    private Date lrDate;
    private String consignor;
    private String destinationFrom;
    private String destinationTo;
    private String vehicleNo;
    private String weight;
    private int packageQuantity;
    private String packageType;
    private String invoiceNo;
    private int billAmount;
    private String billRate;

    public InvoiceFModel(String billNo, int lrNo, Date billDate, Date lrDate, String consignor,
                         String destinationFrom, String destinationTo, String vehicleNo, String weight,
                         int packageQuantity, String packageType, String invoiceNo, int billAmount, String billRate) {
        this.billNo = billNo;
        this.lrNo = lrNo;
        this.billDate = billDate;
        this.lrDate = lrDate;
        this.consignor = consignor;
        this.destinationFrom = destinationFrom;
        this.destinationTo = destinationTo;
        this.vehicleNo = vehicleNo;
        this.weight = weight;
        this.packageQuantity = packageQuantity;
        this.packageType = packageType;
        this.invoiceNo = invoiceNo;
        this.billAmount = billAmount;
        this.billRate = billRate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getLrNo() {
        return lrNo;
    }

    public void setLrNo(int lrNo) {
        this.lrNo = lrNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Date getLrDate() {
        return lrDate;
    }

    public void setLrDate(Date lrDate) {
        this.lrDate = lrDate;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(String destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public String getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(String destinationTo) {
        this.destinationTo = destinationTo;
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

    public int getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(int packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillRate() {
        return billRate;
    }

    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    @Override
    public String toString() {
        return "InvoiceFModel{" +
                "billNo='" + billNo + '\'' +
                ", lrNo=" + lrNo +
                ", billDate=" + billDate +
                ", lrDate=" + lrDate +
                ", consignor='" + consignor + '\'' +
                ", destinationFrom='" + destinationFrom + '\'' +
                ", destinationTo='" + destinationTo + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", weight='" + weight + '\'' +
                ", packageQuantity=" + packageQuantity +
                ", packageType='" + packageType + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", billAmount=" + billAmount +
                ", billRate='" + billRate + '\'' +
                '}';
    }
}
