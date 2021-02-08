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

public class AdditionalCharges implements Initializable {

    //controls
    @FXML
    private TextField addCharge;

    @FXML
    private Button btnAdd;

    @FXML
    private ListView addChargesList;

    ListDB listDB = new ListDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();
        addChargesList.setItems(listDB.getAddChargesList());
    }

    public void onClickAdd(MouseEvent mouseEvent) {
        listDB.insertAddCharge(addCharge.getText());
        addCharge.clear();
        addChargesList.setItems(listDB.getAddChargesList());
    }

    private void createBooleanBinding(){
        BooleanBinding addChargeValid = Bindings.createBooleanBinding(()->{
            if(addCharge.getText().isEmpty())
                return false;
            else
                return true;

        }, addCharge.textProperty());

        btnAdd.disableProperty().bind(addChargeValid.not());
    }

}
