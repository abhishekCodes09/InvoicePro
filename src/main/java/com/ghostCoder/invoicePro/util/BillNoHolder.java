package com.ghostCoder.invoicePro.util;

public final class BillNoHolder {
    private String billNo;
    private final static BillNoHolder INSTANCE = new BillNoHolder();

    private BillNoHolder(){}

    public static BillNoHolder  getInstance(){
        return INSTANCE;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}
