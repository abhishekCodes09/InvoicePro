package com.ghostCoder.invoicePro.models;

import java.sql.Date;

public class InvoiceModel {
    private String billNo;
    private int lrNo;
    private Date date;
    private int amount;
    private String rate;
    private boolean paid;
    private Date paymentDate;
    private String chequeNo;
    private long chequeAmount;
    private boolean multiLr;

    public InvoiceModel(String billNo, int lrNo, Date date) {
        this.billNo = billNo;
        this.lrNo = lrNo;
        this.date = date;
    }

    public InvoiceModel(String billNo, int lrNo, Date date, int amount, String rate, boolean paid, Date paymentDate,
                        String chequeNo, long chequeAmount, boolean multiLr) {
        this.billNo = billNo;
        this.lrNo = lrNo;
        this.date = date;
        this.amount = amount;
        this.rate = rate;
        this.paid = paid;
        this.paymentDate = paymentDate;
        this.chequeNo = chequeNo;
        this.chequeAmount = chequeAmount;
        this.multiLr = multiLr;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public long getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(long chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public boolean isMultiLr() {
        return multiLr;
    }

    public void setMultiLr(boolean multiLr) {
        this.multiLr = multiLr;
    }

    @Override
    public String toString() {
        return "InvoiceModel{" +
                "billNo='" + billNo + '\'' +
                ", lrNo=" + lrNo +
                ", date=" + date +
                ", amount=" + amount +
                ", rate='" + rate + '\'' +
                ", paid=" + paid +
                ", paymentDate=" + paymentDate +
                ", chequeNo='" + chequeNo + '\'' +
                ", chequeAmount=" + chequeAmount +
                ", multiLr=" + multiLr +
                '}';
    }
}
