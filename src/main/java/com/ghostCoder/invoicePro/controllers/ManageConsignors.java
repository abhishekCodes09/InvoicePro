package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.ConsignorDB;
import com.ghostCoder.invoicePro.models.ConsignorModel;
import com.ghostCoder.invoicePro.validator.FormValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageConsignors implements Initializable {
    //window manager object
    WindowManager windowManager = new WindowManager();

    //form validator object
    FormValidator formValidator = new FormValidator();

    //controls
    @FXML
    private TextField cname, addL1, addL2, gstNo, emailId;

    @FXML
    private ComboBox city;

    @FXML
    private ListView consignorList;

    @FXML
    private AnchorPane manageConsignorPane;

    @FXML
    private Button btnAdd;

    //cities list
    ObservableList<String> citiesList;

    ConsignorDB consignorDB = new ConsignorDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();

        //acceptable range for fields
        cname.setTextFormatter(formValidator.rangeValidator(50));
        addL1.setTextFormatter(formValidator.rangeValidator(40));
        addL2.setTextFormatter(formValidator.rangeValidator(35));
        gstNo.setTextFormatter(formValidator.rangeValidator(15));
        emailId.setTextFormatter(formValidator.rangeValidator(60));

        consignorList.setItems(consignorDB.getConsignorsList());

        //read json array list of cities
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("cities-name-list.json")){
            //read json config file
            Object citiesObj = jsonParser.parse(reader);

            //store data in json array
            JSONArray cities = (JSONArray)citiesObj;
            citiesList = FXCollections.observableArrayList(cities);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        city.setItems(citiesList);

    }

    //create new consignor
    public void onClickAdd(MouseEvent mouseEvent){
        if(!formValidator.emailValidator(emailId.getText())){
            windowManager.createAlert("Invalid Email!", "The Email You Provided Is Invalid, Please" +
                    "Enter A Valid Email!", Alert.AlertType.INFORMATION, manageConsignorPane);
        }
        else{
            ConsignorModel consignorData = new ConsignorModel(cname.getText(), addL1.getText(), addL2.getText(),
                    city.getSelectionModel().getSelectedItem().toString(), gstNo.getText(),
                    emailId.getText());
            consignorDB.insertData(consignorData);
            consignorList.setItems(consignorDB.getConsignorsList());
        }
        resetForm();
    }

    private void createBooleanBinding(){
        BooleanBinding cnameValid = Bindings.createBooleanBinding(()->{
            if(cname.getText().isEmpty())
                return false;
            else
                return true;

        }, cname.textProperty());

        BooleanBinding addL1Valid = Bindings.createBooleanBinding(()->{
            if(addL1.getText().isEmpty())
                return false;
            else
                return true;

        }, addL1.textProperty());

        BooleanBinding cityValid = Bindings.createBooleanBinding(()->{
            if(city.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, city.getSelectionModel().selectedItemProperty());

        BooleanBinding gstNoValid = Bindings.createBooleanBinding(()->{
            if(gstNo.getText().isEmpty())
                return false;
            else
                return true;

        }, gstNo.textProperty());

        BooleanBinding emailValid = Bindings.createBooleanBinding(()->{
            if(emailId.getText().isEmpty())
                return false;
            else
                return true;

        }, emailId.textProperty());

        btnAdd.disableProperty().bind(cnameValid.not().or(addL1Valid.not().or(cityValid.not().or(gstNoValid.not().or(
                emailValid.not()
        )))));
    }

    private void resetForm(){
        cname.clear();
        addL1.clear();
        addL2.clear();
        city.getSelectionModel().clearSelection();
        gstNo.clear();
        emailId.clear();
    }
}
