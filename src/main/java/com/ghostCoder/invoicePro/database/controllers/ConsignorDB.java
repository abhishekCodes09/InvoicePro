package com.ghostCoder.invoicePro.database.controllers;

import com.ghostCoder.invoicePro.database.AppDB;
import com.ghostCoder.invoicePro.models.ConsignorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsignorDB {
    private static final String TABLE_NAME = "consignors";

    AppDB appDB;

    public ConsignorDB(){
        appDB = new AppDB();
    }

    public void insertData(ConsignorModel consignorData){
        PreparedStatement preparedStatement;

        String query = "INSERT INTO "+TABLE_NAME+" VALUES (?,?,?,?,?,?,?)";


        try {
            preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, consignorData.getCname());
            preparedStatement.setString(2, consignorData.getAddressL1());
            preparedStatement.setString(3, consignorData.getAddressL2());
            preparedStatement.setString(4, consignorData.getCity());
            preparedStatement.setString(5, consignorData.getGstNo());
            preparedStatement.setString(6, consignorData.getEmailId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList getConsignorsList(){
        ObservableList<String> consignorsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM "+TABLE_NAME+";";

        ResultSet consignorRS = appDB.retrieveData(query);

        try{
            while(consignorRS.next()){
                consignorsList.add(consignorRS.getString("cname"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  consignorsList;
    }

    public ConsignorModel getConsignorDetails(String consignorName){
        ConsignorModel consignor = null;
        String query = "SELECT * FROM "+TABLE_NAME+" where cname='"+consignorName+"';";

        ResultSet consignorRS = appDB.retrieveData(query);

        try{
            while(consignorRS.next()){
                consignor = new ConsignorModel(
                        consignorRS.getString("cname"),
                        consignorRS.getString("addressL1"),
                        consignorRS.getString("addressL2"),
                        consignorRS.getString("city"),
                        consignorRS.getString("gstNo"),
                        consignorRS.getString("emailId"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  consignor;
    }
}
