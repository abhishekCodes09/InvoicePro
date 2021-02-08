package com.ghostCoder.invoicePro.database;

import java.sql.*;

public class AppDB {

    private static Connection conn;

    //establish connection with database
    public void createConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:invoicePro.db");
        }catch(ClassNotFoundException | SQLException c){
            System.out.println("Error while creating database connection! ");
            System.out.print(c.getMessage());
            String password = "eGE3wwNYb2cD668sS+1ClA==";
        }
    }

    public Connection getConn(){
        return conn;
    }

    //close the database connection
    public void closeConnection(){
        try {
            conn.close();
        }catch (SQLException s){
            System.out.println("Error while closing database connection");
        }
    }

    //creating tables
    public void createTable(String query) {
        Statement createTableStmt;

        try {
            createTableStmt = conn.createStatement();
            createTableStmt.execute(query);
            createTableStmt.close();

        }catch (SQLException e){
            System.out.println("Error while creating table");
            System.err.println(e.getMessage());
        }
    }

    //insert data
    public void dmlQuery(String query){
        Statement dmlStmt;
        try{
            dmlStmt = conn.createStatement();
            dmlStmt.executeUpdate(query);
            dmlStmt.close();
        }catch (SQLException e){
            System.out.println("Error while inserting data into table");
            System.err.println(e.getMessage());
        }
    }

    //retrieve data
    public ResultSet retrieveData(String query){
        Statement retrieveStmt;
        ResultSet resultSet = null;
        try{
            retrieveStmt = conn.createStatement();
            resultSet = retrieveStmt.executeQuery(query);
        }catch (SQLException e){
            System.out.println("Error while retrieving data from database");
            System.err.println(e.getMessage());
        }
        return resultSet;
    }
}
