package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.database.controllers.ListDB;
import com.ghostCoder.invoicePro.validator.FormValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VehicleWeight implements Initializable {

    //controls
    @FXML
    private TextField vehicleWeight;

    @FXML
    private Button btnAdd;

    @FXML
    private ListView vehicleWeightList;

    ListDB listDB = new ListDB();
    FormValidator formValidator = new FormValidator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();
        vehicleWeight.setTextFormatter(formValidator.rangeValidator(20));
        vehicleWeightList.setItems(listDB.getVehicleWeightList());
    }

    public void onClickAdd(MouseEvent mouseEvent){
        listDB.addVehicleWeight(vehicleWeight.getText());
        vehicleWeightList.setItems(listDB.getVehicleWeightList());

        //reset field
        vehicleWeight.clear();
    }

    private void createBooleanBinding(){
        BooleanBinding vehicleWeightValid = Bindings.createBooleanBinding(()->{
            if(vehicleWeight.getText().isEmpty())
                return false;
            else
                return true;

        }, vehicleWeight.textProperty());

        btnAdd.disableProperty().bind(vehicleWeightValid.not());
    }
}
