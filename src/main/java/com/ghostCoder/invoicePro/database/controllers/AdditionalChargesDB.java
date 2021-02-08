package com.ghostCoder.invoicePro.database.controllers;

import com.ghostCoder.invoicePro.database.AppDB;
import com.ghostCoder.invoicePro.models.AdditionalChargesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdditionalChargesDB {
    private static final String TABLE_NAME = "additionalChargesRecords";
    AppDB appDB;

    public AdditionalChargesDB(){
        appDB = new AppDB();
    }

    public void insertData(AdditionalChargesModel additionalCharge){
        PreparedStatement preparedStatement;

        String query = "INSERT INTO "+TABLE_NAME+" VALUES (?,?,?,?,?)";

        try{
            preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, additionalCharge.getBillNo());
            preparedStatement.setString(2, additionalCharge.getChargeName());
            preparedStatement.setString(3, additionalCharge.getAdditionalInfo());
            preparedStatement.setInt(4, additionalCharge.getAmount());
            preparedStatement.setString(5, additionalCharge.getRate());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public ObservableList getAdditionalCharge(String billNo){
        ObservableList<AdditionalChargesModel> additionalCharges = FXCollections.observableArrayList();

        String query = "SELECT * FROM "+TABLE_NAME+ " WHERE billNo="+billNo+";";

        ResultSet addChargeRS = appDB.retrieveData(query);

        try{
            while(addChargeRS.next()){
                additionalCharges.add(new AdditionalChargesModel(
                                addChargeRS.getString("billNo"),
                                addChargeRS.getString("chargeName"),
                                addChargeRS.getString("additionalInfo"),
                                addChargeRS.getInt("amount"),
                                addChargeRS.getString("rate")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  additionalCharges;
    }
}
