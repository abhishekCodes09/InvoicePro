package com.ghostCoder.invoicePro.models;

public class InvoiceAddChargeModel {

    private String chargeName;
    private int amount;
    private String rate;
    private String paisa;

    public InvoiceAddChargeModel(String chargeName, int amount, String rate, String paisa) {
        this.chargeName = chargeName;
        this.amount = amount;
        this.rate = rate;
        this.paisa = paisa;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
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
