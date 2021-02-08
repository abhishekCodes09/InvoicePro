package com.ghostCoder.invoicePro.models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListModel {

    private ObservableList<String> additionalCharges;
    private ObservableList<String> materialType;
    private ObservableList<String> rateType;
    private ObservableList<String> vehicleWeight;

    public ListModel() {
        additionalCharges = FXCollections.observableArrayList();
        materialType = FXCollections.observableArrayList();
        rateType = FXCollections.observableArrayList();
        vehicleWeight = FXCollections.observableArrayList();
    }

    public void addAdditionalCharge(String charge){
        additionalCharges.add(charge);
    }

    public ObservableList getAdditionalChargeList(){
        return  additionalCharges;
    }

    public void addMaterialType(String type){
        materialType.add(type);
    }

    public ObservableList getMaterialTypeList(){
        return materialType;
    }

    public void addRateType(String type){
        rateType.add(type);
    }

    public ObservableList getRateTypeList(){
        return rateType;
    }

    public void addVehicleWeight(String weight){
        vehicleWeight.add(weight);
    }

    public ObservableList getVehicleWeightList(){
        return vehicleWeight;
    }


}
