package com.ghostCoder.invoicePro.models;

import java.sql.Date;

public class LrModel {
    private String consignor;
    private Date date;
    private int lrNo;
    private String destinationFrom;
    private String destinationTo;
    private String vehicleNo;
    private String weight;
    private int packageQuantity;
    private String packageType;
    private String invoiceNo;

    public LrModel(String consignor, Date date, int lrNo, String destinationFrom, String destinationTo, String vehicleNo,
                   String weight, int packageQuantity, String packageType, String invoiceNo) {
        this.consignor = consignor;
        this.date = date;
        this.lrNo = lrNo;
        this.destinationFrom = destinationFrom;
        this.destinationTo = destinationTo;
        this.vehicleNo = vehicleNo;
        this.weight = weight;
        this.packageQuantity = packageQuantity;
        this.packageType = packageType;
        this.invoiceNo = invoiceNo;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLrNo() {
        return lrNo;
    }

    public void setLrNo(int lrNo) {
        this.lrNo = lrNo;
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

    @Override
    public String toString() {
        return "LrModel{" +
                "consignor='" + consignor + '\'' +
                ", date=" + date +
                ", lrNo=" + lrNo +
                ", destinationFrom='" + destinationFrom + '\'' +
                ", destinationTo='" + destinationTo + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", weight='" + weight + '\'' +
                ", packageQuantity=" + packageQuantity +
                ", packageType='" + packageType + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                '}';
    }
}
