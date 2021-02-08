package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.ConsignorDB;
import com.ghostCoder.invoicePro.database.controllers.ListDB;
import com.ghostCoder.invoicePro.database.controllers.LrDB;
import com.ghostCoder.invoicePro.models.LrModel;
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
import java.sql.Date;
import java.util.ResourceBundle;


public class CreateLR implements Initializable {
    //window manager object
    WindowManager windowManager = new WindowManager();

    //form validator object
    FormValidator formValidator = new FormValidator();

    //controls
    @FXML
    private ComboBox consignor, destinationFrom, destinationTo, weight, type;

    @FXML
    private DatePicker date;

    @FXML
    private TextField lrNo, vehicleNo, invoiceNo, quantity;

    @FXML
    private Button btnCreate;

    @FXML
    private AnchorPane createLrPane;

    ListDB listDB = new ListDB();
    ConsignorDB consignorDB = new ConsignorDB();
    LrDB lrDB = new LrDB();

    //cities list
    ObservableList<String> citiesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //assigning validators
        lrNo.setTextFormatter(formValidator.numericValidator());
        quantity.setTextFormatter(formValidator.numericValidator());
        vehicleNo.setTextFormatter(formValidator.rangeValidator(11));


        createBooleanBindings();

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

        destinationFrom.setItems(citiesList);
        destinationTo.setItems(citiesList);
        weight.setItems(listDB.getVehicleWeightList());
        type.setItems(listDB.getMaterialTypeList());
        consignor.setItems(consignorDB.getConsignorsList());
    }

    //boolean binding to disable the create button if fields are empty
    private void createBooleanBindings(){

        BooleanBinding isConsignorValid = Bindings.createBooleanBinding(()->{
            if(consignor.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, consignor.getSelectionModel().selectedItemProperty());

        BooleanBinding isLrValid = Bindings.createBooleanBinding(()->{
            if(lrNo.getText().isEmpty())
                return false;
            else
                return true;
        }, lrNo.textProperty());

        BooleanBinding isDestinationFromValid = Bindings.createBooleanBinding(()->{
            if(destinationFrom.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, destinationFrom.getSelectionModel().selectedItemProperty());

        BooleanBinding isDestinationToValid = Bindings.createBooleanBinding(()->{
            if(destinationTo.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, destinationTo.getSelectionModel().selectedItemProperty());

        BooleanBinding isVehicleNoValid = Bindings.createBooleanBinding(()->{
            if(vehicleNo.getText().isEmpty())
                return false;
            else
                return true;
        }, vehicleNo.textProperty());

        BooleanBinding isWeightValid = Bindings.createBooleanBinding(()->{
            if(weight.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, weight.getSelectionModel().selectedItemProperty());

        BooleanBinding isQuantityValid = Bindings.createBooleanBinding(()->{
            if(quantity.getText().isEmpty())
                return false;
            else
                return true;
        }, quantity.textProperty());

        BooleanBinding isTypeValid = Bindings.createBooleanBinding(()->{
            if(type.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, type.getSelectionModel().selectedItemProperty());

        BooleanBinding isInvoiceNoValid = Bindings.createBooleanBinding(()->{
            if(invoiceNo.getText().isEmpty())
                return false;
            else
                return true;
        }, invoiceNo.textProperty());

        btnCreate.disableProperty().bind(isConsignorValid.not().or(isLrValid.not().or(isDestinationFromValid.not().or(
                isDestinationToValid.not().or(isVehicleNoValid.not().or(isWeightValid.not().or(isQuantityValid.not().or(
                        isTypeValid.not().or(isInvoiceNoValid.not())
                ))))
        ))));
    }

    public void onCreateLrRecord(MouseEvent mouseEvent){
        Date lrDate = Date.valueOf(date.getValue());
        LrModel lrData = new LrModel(consignor.getSelectionModel().getSelectedItem().toString(),
                lrDate, Integer.parseInt(lrNo.getText()),
                destinationFrom.getSelectionModel().getSelectedItem().toString(),
                destinationTo.getSelectionModel().getSelectedItem().toString(),
                vehicleNo.getText(), weight.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(quantity.getText()),
                type.getSelectionModel().getSelectedItem().toString(), invoiceNo.getText());

        //System.out.println(lrData);
        lrDB.insertData(lrData);
        windowManager.createAlert("Success", "New LR Record Created Successfully",
                Alert.AlertType.INFORMATION, createLrPane);

        resetForm();
    }

    private void resetForm(){
        consignor.getSelectionModel().clearSelection();
        date.getEditor().clear();
        lrNo.clear();
        destinationFrom.getSelectionModel().clearSelection();
        destinationTo.getSelectionModel().clearSelection();
        vehicleNo.clear();
        weight.getSelectionModel().clearSelection();
        quantity.clear();
        type.getSelectionModel().clearSelection();
        invoiceNo.clear();
    }
}
