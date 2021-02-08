package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.AdditionalChargesDB;
import com.ghostCoder.invoicePro.database.controllers.InvoiceDB;
import com.ghostCoder.invoicePro.database.controllers.ListDB;
import com.ghostCoder.invoicePro.models.AdditionalChargesModel;
import com.ghostCoder.invoicePro.models.InvoiceModel;
import com.ghostCoder.invoicePro.validator.FormValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CreateInvoice implements Initializable {
    //window manager object
    WindowManager windowManager = new WindowManager();

    //form validator object
    FormValidator formValidator = new FormValidator();

    //controls
    @FXML
    private TextField billNo, lrNo, billAmount, addInfo, addAmount;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox billRate, addCharge, addRate;

    @FXML
    private ListView lrList;

    @FXML
    private TableView addChargeTable;

    @FXML
    private AnchorPane createInvoicePane;

    @FXML
    private Button btnCreate, btnAddLr, btnRemoveLr, btnAddCharge, btnRemoveCharge;

    ListDB listDB = new ListDB();
    InvoiceDB invoiceDB = new InvoiceDB();
    AdditionalChargesDB chargesDB = new AdditionalChargesDB();

    ObservableList<Integer> lrObsList = FXCollections.observableArrayList();
    ObservableList<AdditionalChargesModel> addChargeObsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //add validators
        lrNo.setTextFormatter(formValidator.numericValidator());
        billAmount.setTextFormatter(formValidator.numericValidator());
        addAmount.setTextFormatter(formValidator.numericValidator());

        createBooleanBinding();

        billRate.setItems(listDB.getRateTypeList());
        addRate.setItems(listDB.getRateTypeList());
        addCharge.setItems(listDB.getAddChargesList());

        //add charge table columns
        TableColumn chargeName = new TableColumn("Charge");
        TableColumn additionalInfo = new TableColumn("Info");
        TableColumn amount = new TableColumn("Amount");
        TableColumn rate = new TableColumn("Rate");

        addChargeTable.getColumns().addAll(chargeName, additionalInfo, amount, rate);

        chargeName.setCellValueFactory(new PropertyValueFactory<AdditionalChargesModel, String>("chargeName"));
        additionalInfo.setCellValueFactory(new PropertyValueFactory<AdditionalChargesModel, String>("additionalInfo"));
        amount.setCellValueFactory(new PropertyValueFactory<AdditionalCharges, Integer>("amount"));
        rate.setCellValueFactory(new PropertyValueFactory<AdditionalChargesModel, String>("rate"));

    }



    public void onClickAddLr(MouseEvent mouseEvent){
        lrObsList.add(Integer.parseInt(lrNo.getText()));
        lrList.setItems(lrObsList);
        lrNo.setText("");
    }

    public void onClickRemoveLr(MouseEvent mouseEvent){
        lrObsList.remove(lrList.getSelectionModel().getSelectedItem());
    }

    public void onClickAddCharge(MouseEvent mouseEvent){
        addChargeObsList.add(new AdditionalChargesModel(billNo.getText(),
                addCharge.getSelectionModel().getSelectedItem().toString(),
                addInfo.getText(), Integer.parseInt(addAmount.getText()),
                addRate.getSelectionModel().getSelectedItem().toString()));

        addChargeTable.setItems(addChargeObsList);
    }

    public void onClickRemoveCharge(MouseEvent mouseEvent){
        addChargeObsList.remove(addChargeTable.getSelectionModel().getSelectedItem());
    }

    private void createBooleanBinding(){

        //is lr no field valid
        BooleanBinding isLrNoValid = Bindings.createBooleanBinding(()->{
            if(lrNo.getText().isEmpty())
                return false;
            else
                return true;
        }, lrNo.textProperty());

        //is bill no field valid
        BooleanBinding isBillNoValid = Bindings.createBooleanBinding(()->{
            if(billNo.getText().isEmpty())
                return false;
            else
                return true;
        }, billNo.textProperty());

        //is bill amount field valid
        BooleanBinding isBillAmountValid = Bindings.createBooleanBinding(()->{
            if(billAmount.getText().isEmpty())
                return false;
            else
                return true;
        }, billAmount.textProperty());

        //is bill rate selected
        BooleanBinding isBillRateValid = Bindings.createBooleanBinding(()->{
            if(billRate.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, billRate.getSelectionModel().selectedItemProperty());

        //is add rate selected
        BooleanBinding isAddRateValid = Bindings.createBooleanBinding(()->{
            if(addRate.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, addRate.getSelectionModel().selectedItemProperty());

        //is add charge rate selected
        BooleanBinding isAddChargeSelected = Bindings.createBooleanBinding(()->{
            if(addChargeTable.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, addChargeTable.getSelectionModel().selectedItemProperty());

        //is lr no from the lr list is selected
        BooleanBinding isLrSelected = Bindings.createBooleanBinding(()->{
            if(lrList.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, lrList.getSelectionModel().selectedItemProperty());

        //is add charge info field valid
        BooleanBinding isAddInfoValid = Bindings.createBooleanBinding(()->{
            if(addInfo.getText().isEmpty())
                return false;
            else
                return true;
        }, addInfo.textProperty());

        //is add charge amount field valid
        BooleanBinding isAddAmountValid = Bindings.createBooleanBinding(()->{
            if(addAmount.getText().isEmpty())
                return false;
            else
                return true;
        }, addAmount.textProperty());

        //is add charge selected
        BooleanBinding isAddChargeValid = Bindings.createBooleanBinding(()->{
            if(addCharge.getSelectionModel().isEmpty())
                return false;
            else
                return true;
        }, addCharge.getSelectionModel().selectedItemProperty());

        //disable buttons when the fields are invalid
        btnCreate.disableProperty().bind(isBillNoValid.not().or(Bindings.size(lrObsList).isEqualTo(0).or
                (isBillAmountValid.not().or(
                isBillRateValid.not()
        ))));

        btnAddLr.disableProperty().bind(isLrNoValid.not());
        btnAddCharge.disableProperty().bind(isAddChargeValid.not().or(isAddRateValid.not().or(
                isAddAmountValid.not()
        )));

        btnRemoveCharge.disableProperty().bind(isAddChargeSelected.not());
        btnRemoveLr.disableProperty().bind(isLrSelected.not());
    }


    public void onClickCreate(MouseEvent mouseEvent){
        int billCount = 1;
        for(int lrNo: lrObsList) {
            if (billCount == 1) {
                InvoiceModel invoiceData = new InvoiceModel(billNo.getText(), lrNo, Date.valueOf(date.getValue()),
                        Integer.parseInt(billAmount.getText()), billRate.getSelectionModel().getSelectedItem().toString(),
                        false, null, null, 0, true);
                invoiceDB.insertData(invoiceData);
                System.out.println(invoiceData);
                billCount++;
            } else {
                InvoiceModel invoiceData = new InvoiceModel(billNo.getText(), lrNo, Date.valueOf(date.getValue()));
                System.out.println(invoiceData);
                invoiceDB.insertData(invoiceData);
            }
        }

        if(!addChargeObsList.isEmpty()){
            for(AdditionalChargesModel addCharge: addChargeObsList){
                System.out.println(addCharge);
                chargesDB.insertData(addCharge);
            }
        }
        
        windowManager.createAlert("Success", "New Invoice Record Created Successfully",
                Alert.AlertType.INFORMATION, createInvoicePane);

        //reset all the fields on scene
        resetForm();
    }

    private void resetForm(){
        //clear fields
        billNo.clear();
        date.getEditor().clear();
        lrNo.clear();
        lrList.getItems().clear();
        billAmount.clear();
        billRate.getSelectionModel().clearSelection();
        addCharge.getSelectionModel().clearSelection();
        addInfo.clear();
        addRate.getSelectionModel().clearSelection();
        addAmount.clear();
        addChargeTable.getItems().clear();

        //clear lists
        lrObsList.clear();
        addChargeObsList.clear();
    }
}
