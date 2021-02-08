package com.ghostCoder.invoicePro.models;

public class TempTableData {

    //properties
    private int lr_no;
    private String date;
    private String consignor;
    private String destinationFrom;
    private String destinationTo;


    public TempTableData(int lr_no, String date, String consignor, String destinationFrom, String destinationTo) {
        this.lr_no = lr_no;
        this.date = date;
        this.consignor = consignor;
        this.destinationFrom = destinationFrom;
        this.destinationTo = destinationTo;
    }

    public int getLr_no() {
        return lr_no;
    }

    public void setLr_no(int lr_no) {
        this.lr_no = lr_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
