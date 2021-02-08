package com.ghostCoder.invoicePro.database.controllers;

import com.ghostCoder.invoicePro.database.AppDB;
import com.ghostCoder.invoicePro.models.LrModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LrDB {
    private static final String TABLE_NAME = "lrRecords";

    AppDB appDB;

    public LrDB(){
        appDB = new AppDB();
    }

    public void insertData(LrModel lrData){
        PreparedStatement preparedStatement;

        String query = "INSERT INTO "+TABLE_NAME+" VALUES (?,?,?,?,?,?,?,?,?,?)";

        try{
            preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, lrData.getConsignor());
            preparedStatement.setDate(2, Date.valueOf(lrData.getDate().toString()));
            preparedStatement.setInt(3, lrData.getLrNo());
            preparedStatement.setString(4, lrData.getDestinationFrom());
            preparedStatement.setString(5, lrData.getDestinationTo());
            preparedStatement.setString(6, lrData.getVehicleNo());
            preparedStatement.setString(7, lrData.getWeight());
            preparedStatement.setInt(8, lrData.getPackageQuantity());
            preparedStatement.setString(9, lrData.getPackageType());
            preparedStatement.setString(10, lrData.getInvoiceNo());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //get data by lr number
    public ObservableList<LrModel> getDataByLrNo(int lrNo){
        ObservableList<LrModel> lrData= FXCollections.observableArrayList();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE lrNo=?;";
        try {
            PreparedStatement preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setInt(1, lrNo);
            try(ResultSet lrResultSet = preparedStatement.executeQuery()){
                while (lrResultSet.next()){
                    lrData.add(new LrModel(
                            lrResultSet.getString("consignor"),
                            lrResultSet.getDate("date"),
                            lrResultSet.getInt("lrNo"),
                            lrResultSet.getString("destinationFrom"),
                            lrResultSet.getString("destinationTo"),
                            lrResultSet.getString("vehicleNo"),
                            lrResultSet.getString("weight"),
                            lrResultSet.getInt("packageQuantity"),
                            lrResultSet.getString("packageType"),
                            lrResultSet.getString("invoiceNo")
                    ));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lrData;
    }

    //get data by consignor name
    public ObservableList<LrModel> getDataByConsignor(String consignor, String year){
        ObservableList<LrModel> lrData= FXCollections.observableArrayList();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE consignor=? AND strftime('%Y',date/1000, 'unixepoch')=?;";

        try{
            PreparedStatement preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, consignor);
            preparedStatement.setString(2, year);

            try(ResultSet lrResultSet = preparedStatement.executeQuery()){
                while (lrResultSet.next()){
                    lrData.add(new LrModel(lrResultSet.getString("consignor"), lrResultSet.getDate("date"),
                            lrResultSet.getInt("lrNo"), lrResultSet.getString("destinationFrom"),
                            lrResultSet.getString("destinationTo"), lrResultSet.getString("vehicleNo"),
                            lrResultSet.getString("weight"), lrResultSet.getInt("packageQuantity"),
                            lrResultSet.getString("packageType"), lrResultSet.getString("invoiceNo")
                    ));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lrData;
    }

    //delete lr record
    public boolean deleteLrRecord(int lrNo){
        String query = "DELETE FROM "+TABLE_NAME+" WHERE lrNo=?;";
        try{
            PreparedStatement preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setInt(1, lrNo);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
