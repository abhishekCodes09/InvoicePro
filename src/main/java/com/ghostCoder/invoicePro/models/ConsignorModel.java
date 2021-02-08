package com.ghostCoder.invoicePro.models;

public class ConsignorModel {
    private String cname;
    private String addressL1;
    private String addressL2;
    private String city;
    private String gstNo;
    private String emailId;

    public ConsignorModel(String cname, String addressL1, String addressL2, String city,
                          String gstNo, String emailId) {
        this.cname = cname;
        this.addressL1 = addressL1;
        this.addressL2 = addressL2;
        this.city = city;
        this.gstNo = gstNo;
        this.emailId = emailId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddressL1() {
        return addressL1;
    }

    public void setAddressL1(String addressL1) {
        this.addressL1 = addressL1;
    }

    public String getAddressL2() {
        return addressL2;
    }

    public void setAddressL2(String addressL2) {
        this.addressL2 = addressL2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "ConsignorModel{" +
                "cname='" + cname + '\'' +
                ", addressL1='" + addressL1 + '\'' +
                ", addressL2='" + addressL2 + '\'' +
                ", city='" + city + '\'' +
                ", gstNo='" + gstNo + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
