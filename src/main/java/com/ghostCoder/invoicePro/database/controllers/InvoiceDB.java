package com.ghostCoder.invoicePro.database.controllers;

import com.ghostCoder.invoicePro.database.AppDB;
import com.ghostCoder.invoicePro.models.InvoiceFModel;
import com.ghostCoder.invoicePro.models.InvoiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InvoiceDB {
    private static final String INVOICE_TABLE = "invoiceRecords";
    private static final String LR_TABLE = "lrRecords";

    AppDB appDB;

    public InvoiceDB(){
        appDB = new AppDB();
    }

    public void insertData(InvoiceModel invoiceData){
        PreparedStatement preparedStatement;

        String query = "INSERT INTO "+INVOICE_TABLE+" VALUES (?,?,?,?,?,?,?,?,?,?)";

        try{
            preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, invoiceData.getBillNo());
            preparedStatement.setDate(2, invoiceData.getDate());
            preparedStatement.setInt(3,  invoiceData.getLrNo());
            preparedStatement.setInt(4, invoiceData.getAmount());
            preparedStatement.setString(5, invoiceData.getRate());
            preparedStatement.setBoolean(6, invoiceData.isPaid());
            preparedStatement.setDate(7, (Date) invoiceData.getPaymentDate());
            preparedStatement.setString(8, invoiceData.getChequeNo());
            preparedStatement.setLong(9, invoiceData.getChequeAmount());
            preparedStatement.setBoolean(10, invoiceData.isMultiLr());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList getInvoiceDetailsByBillNo(String billNo){
        ObservableList<InvoiceFModel> invoiceList = FXCollections.observableArrayList();

        String query = "SELECT i.billNo, i.date as 'billDate', i.amount, i.rate, i.lrNo, l.date as 'lrDate', " +
                "l.consignor, l.destinationFrom, l.destinationTo, l.vehicleNo, l.weight, l.packageQuantity, " +
                "l.packageType, l.invoiceNo FROM "+INVOICE_TABLE+" AS i INNER JOIN "+LR_TABLE+" " +
                "as l ON i.lrNo=l.lrNo WHERE i.billNo=? AND i.amount IS NOT NULL";

        try{
            PreparedStatement preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, billNo);

            try(ResultSet invoiceRS = preparedStatement.executeQuery()){
                while(invoiceRS.next()){
                    invoiceList.add(new InvoiceFModel(invoiceRS.getString("billNo"),
                            invoiceRS.getInt("lrNo"),
                            invoiceRS.getDate("billDate"),
                            invoiceRS.getDate("lrDate"),
                            invoiceRS.getString("consignor"),
                            invoiceRS.getString("destinationFrom"),
                            invoiceRS.getString("destinationTo"),
                            invoiceRS.getString("vehicleNo"),
                            invoiceRS.getString("weight"),
                            invoiceRS.getInt("packageQuantity"),
                            invoiceRS.getString("packageType"),
                            invoiceRS.getString("invoiceNo"),
                            invoiceRS.getInt("amount"),
                            invoiceRS.getString("rate")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return invoiceList;
    }

    public ObservableList getInvoiceDetailsByConsignor(String consignor, String year){
        ObservableList<InvoiceFModel> invoiceList = FXCollections.observableArrayList();

        String query = "SELECT i.billNo, i.date as 'billDate', i.amount, i.rate, i.lrNo, l.date as 'lrDate', " +
                "l.consignor, l.destinationFrom, l.destinationTo, l.vehicleNo, l.weight, l.packageQuantity, " +
                "l.packageType, l.invoiceNo FROM "+INVOICE_TABLE+" AS i INNER JOIN "+LR_TABLE+" " +
                "as l ON i.lrNo=l.lrNo WHERE l.consignor=? AND strftime('%Y',i.date/1000, 'unixepoch')=? AND i.amount IS NOT NULL";

        try{
            PreparedStatement preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, consignor);
            preparedStatement.setString(2, year);

            try(ResultSet invoiceRS = preparedStatement.executeQuery()){
                while(invoiceRS.next()){
                    invoiceList.add(new InvoiceFModel(invoiceRS.getString("billNo"),
                            invoiceRS.getInt("lrNo"),
                            invoiceRS.getDate("billDate"),
                            invoiceRS.getDate("lrDate"),
                            invoiceRS.getString("consignor"),
                            invoiceRS.getString("destinationFrom"),
                            invoiceRS.getString("destinationTo"),
                            invoiceRS.getString("vehicleNo"),
                            invoiceRS.getString("weight"),
                            invoiceRS.getInt("packageQuantity"),
                            invoiceRS.getString("packageType"),
                            invoiceRS.getString("invoiceNo"),
                            invoiceRS.getInt("amount"),
                            invoiceRS.getString("rate")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return invoiceList;
    }

    //delete invoice record
    public boolean deleteInvoiceRecord(String billNo){
        String query = "DELETE FROM "+INVOICE_TABLE+" WHERE billNo=?;";
        try{
            PreparedStatement preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, billNo);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
