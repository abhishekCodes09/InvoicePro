package com.ghostCoder.invoicePro.database.controllers;

import com.ghostCoder.invoicePro.database.AppDB;
import com.ghostCoder.invoicePro.models.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {

    private static final String TABLE_NAME = "user";

    public void insertData(UserModel userData){
        AppDB appDB = new AppDB();
        PreparedStatement preparedStatement;

        String insertDataQuery = "INSERT INTO "+TABLE_NAME+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            preparedStatement = appDB.getConn().prepareStatement(insertDataQuery);
            preparedStatement.setString(1, userData.getUsername());
            preparedStatement.setString(2, userData.getPassword());
            preparedStatement.setString(3, userData.getSalt());
            preparedStatement.setString(4, userData.getSecurityQue());
            preparedStatement.setString(5, userData.getSecurityAns());
            preparedStatement.setString(6, userData.getTransportName());
            preparedStatement.setString(7, userData.getAddress1());
            preparedStatement.setString(8, userData.getAddress2());
            preparedStatement.setString(9, userData.getCity());
            preparedStatement.setString(10, userData.getMobileNo());
            preparedStatement.setString(11,userData.getTelephoneNo());
            preparedStatement.setString(12, userData.getEmailId());
            preparedStatement.setString(13, userData.getAboutTransport());
            preparedStatement.setString(14, userData.getGstNo());
            preparedStatement.setString(15, userData.getPinCode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserModel getUserData(){
        UserModel userData = null;
        //create query
        String getUserDataQuery = "SELECT * FROM "+TABLE_NAME+";";
        AppDB appDB = new AppDB();

        ResultSet userDataRS = appDB.retrieveData(getUserDataQuery);

        try {
            while (userDataRS.next()) {
                userData = new UserModel(userDataRS.getString("username"),
                        userDataRS.getString("password"),userDataRS.getString("salt"),
                        userDataRS.getString("securityQue"), userDataRS.getString("securityAns"),
                        userDataRS.getString("transportName"), userDataRS.getString("addressL1"),
                        userDataRS.getString("addressL2"),userDataRS.getString("city"),
                        userDataRS.getString("mobileNo"), userDataRS.getString("telephoneNo"),
                        userDataRS.getString("emailId"), userDataRS.getString("aboutTransport"),
                        userDataRS.getString("gstNo"), userDataRS.getString("pinCode")
                );
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return userData;
    }

    public void changePassword(String newPassword){
        AppDB appDB = new AppDB();
        PreparedStatement preparedStatement;

        String query = "UPDATE "+TABLE_NAME+" SET password = ?";

        try {
            preparedStatement = appDB.getConn().prepareStatement(query);
            preparedStatement.setString(1, newPassword);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
