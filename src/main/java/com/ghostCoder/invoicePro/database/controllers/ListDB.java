package com.ghostCoder.invoicePro.database.controllers;

import com.ghostCoder.invoicePro.database.AppDB;
import com.ghostCoder.invoicePro.models.ListModel;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDB {
    private static final String ADD_CHARGES_LIST = "additionalChargeList";
    private static final String MATERIAL_TYPE_LIST = "materialType";
    private static final String RATE_TYPE_LIST = "rateTypeList";
    private static final String VEHICLE_WEIGHT_LIST = "vehicleWeight";

    AppDB appDB;

    public ListDB(){
        appDB = new AppDB();
    }

    public void insertAddCharge(String charge){
        String query = "INSERT INTO "+ADD_CHARGES_LIST+" VALUES ('"+charge+"');";
        appDB.dmlQuery(query);
    }

    public ObservableList getAddChargesList(){
        ListModel addChargesList = new ListModel();

        String query = "SELECT * FROM "+ADD_CHARGES_LIST+";";

        ResultSet addChargesRS = appDB.retrieveData(query);

        try{
            while (addChargesRS.next()){
                addChargesList.addAdditionalCharge(addChargesRS.getString("charges"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return addChargesList.getAdditionalChargeList();
    }

    public void insertMaterialType(String type){
        String query = "INSERT INTO "+MATERIAL_TYPE_LIST+" VALUES ('"+type+"');";
        System.out.println(query);
        appDB.dmlQuery(query);
    }

    public ObservableList getMaterialTypeList(){
        ListModel materialTypeList = new ListModel();

        String query = "SELECT * FROM "+MATERIAL_TYPE_LIST+";";

        ResultSet materialTypeRS = appDB.retrieveData(query);

        try{
            while(materialTypeRS.next()){
                materialTypeList.addMaterialType(materialTypeRS.getString("type"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return materialTypeList.getMaterialTypeList();
    }

    public void insertRateType(String type){
        String query = "INSERT INTO "+RATE_TYPE_LIST+" VALUES ('"+type+"');";
        appDB.dmlQuery(query);
    }

    public ObservableList getRateTypeList(){
        ListModel rateTypeList = new ListModel();

        String query = "SELECT * FROM "+RATE_TYPE_LIST+";";

        ResultSet rateTypeRS = appDB.retrieveData(query);

        try{
            while (rateTypeRS.next()){
                rateTypeList.addRateType(rateTypeRS.getString("type"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rateTypeList.getRateTypeList();
    }

    public void addVehicleWeight(String weight){
        String query = "INSERT INTO "+VEHICLE_WEIGHT_LIST+" VALUES ('"+weight+"');";
        appDB.dmlQuery(query);
    }

    public ObservableList getVehicleWeightList(){
        ListModel vehicleWeightList = new ListModel();

        String query = "SELECT * FROM "+VEHICLE_WEIGHT_LIST+";";

        ResultSet vehicleWeightRS = appDB.retrieveData(query);

        try{
            while(vehicleWeightRS.next()){
                vehicleWeightList.addVehicleWeight(vehicleWeightRS.getString("weight"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehicleWeightList.getVehicleWeightList();
    }

}
