package com.ghostCoder.invoicePro.models;

public class AdditionalChargesModel {

    private String billNo;
    private String chargeName;
    private String additionalInfo;
    private int amount;
    private String rate;

    public AdditionalChargesModel(String billNo, String chargeName, String additionalInfo, int amount, String rate) {
        this.billNo = billNo;
        this.chargeName = chargeName;
        this.additionalInfo = additionalInfo;
        this.amount = amount;
        this.rate = rate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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

    @Override
    public String toString() {
        return "AdditionalChargesModel{" +
                "billNo='" + billNo + '\'' +
                ", chargeName='" + chargeName + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", amount=" + amount +
                ", rate='" + rate + '\'' +
                '}';
    }
}
