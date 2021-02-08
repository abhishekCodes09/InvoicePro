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


public class RateType implements Initializable {

    //controls
    @FXML
    private TextField rateType;

    @FXML
    private Button btnAdd;

    @FXML
    private ListView rateTypeList;

    ListDB listDB = new ListDB();
    FormValidator formValidator = new FormValidator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();
        rateType.setTextFormatter(formValidator.rangeValidator(20));
        rateTypeList.setItems(listDB.getRateTypeList());
    }

    public void onClickAdd(MouseEvent mouseEvent){
        listDB.insertRateType(rateType.getText());
        rateTypeList.setItems(listDB.getRateTypeList());

        //reset field
        rateType.clear();
    }

    private void createBooleanBinding(){
        BooleanBinding rateTypeValid = Bindings.createBooleanBinding(()->{
            if(rateType.getText().isEmpty())
                return false;
            else
                return true;

        }, rateType.textProperty());

        btnAdd.disableProperty().bind(rateTypeValid.not());
    }
}
