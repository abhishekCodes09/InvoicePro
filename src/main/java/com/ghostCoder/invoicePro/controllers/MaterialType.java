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

import java.net.URL;
import java.util.ResourceBundle;

public class MaterialType implements Initializable {

    //controls
    @FXML
    private TextField materialType;

    @FXML
    private Button btnAdd;

    @FXML
    private ListView materialList;

    ListDB listDB = new ListDB();
    FormValidator formValidator = new FormValidator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();
        materialType.setTextFormatter(formValidator.rangeValidator(20));
        materialList.setItems(listDB.getMaterialTypeList());
    }

    public void onClickAdd(MouseEvent mouseEvent){
        listDB.insertMaterialType(materialType.getText());
        materialList.setItems(listDB.getMaterialTypeList());

        //reset field
        materialType.clear();
    }

    private void createBooleanBinding(){
        BooleanBinding materialTypeValid = Bindings.createBooleanBinding(()->{
            if(materialType.getText().isEmpty())
                return false;
            else
                return true;

        }, materialType.textProperty());

        btnAdd.disableProperty().bind(materialTypeValid.not());
    }

}
