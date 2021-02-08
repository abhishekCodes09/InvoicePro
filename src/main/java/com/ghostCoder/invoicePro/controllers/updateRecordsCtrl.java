package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.InvoiceDB;
import com.ghostCoder.invoicePro.database.controllers.LrDB;
import com.ghostCoder.invoicePro.models.InvoiceFModel;
import com.ghostCoder.invoicePro.models.LrModel;
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

public class updateRecordsCtrl implements Initializable {

    private ObservableList<LrModel> lrList = FXCollections.observableArrayList();
    private ObservableList<InvoiceFModel> invoiceList = FXCollections.observableArrayList();

    @FXML
    private TextField lrNo, invoiceNo, invNoToUpdate, chequeNo, chequeAmount;

    @FXML
    private DatePicker payDate;

    @FXML
    private Button btnSearchLr, btnSearchInvoice, btnDelete, btnSearchInvUpdate, btnUpdate;

    @FXML
    private TableView resultTable;

    @FXML
    private Label lblResult;

    @FXML
    private AnchorPane manageRecordsPane;

    FormValidator formValidator = new FormValidator();
    WindowManager windowManager = new WindowManager();
    LrDB lrDB = new LrDB();
    InvoiceDB invoiceDB = new InvoiceDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBindings();
        lrNo.setTextFormatter(formValidator.numericValidator());
        chequeAmount.setTextFormatter(formValidator.numericValidator());
    }

    private void createBooleanBindings(){
        //lr no invalid
        BooleanBinding isLrNoValid = Bindings.createBooleanBinding(()->{
            if(lrNo.getText().isEmpty())
                return false;
            else
                return true;
        },  lrNo.textProperty());

        //invoice no invalid
        BooleanBinding isInvoiceNoValid = Bindings.createBooleanBinding(()->{
            if(invoiceNo.getText().isEmpty())
                return false;
            else
                return true;
        },  invoiceNo.textProperty());

        //invoice no for update invalid
        BooleanBinding isInvNoToUpdateValid = Bindings.createBooleanBinding(()->{
            if(invNoToUpdate.getText().isEmpty())
                return false;
            else
                return true;
        },  invNoToUpdate.textProperty());

        //payment details invalid
        BooleanBinding isPayDateValid = Bindings.createBooleanBinding(()->{
            if(payDate.getValue() == null)
                return false;
            else
                return true;
        },  payDate.getProperties());

        BooleanBinding isChequeNoValid = Bindings.createBooleanBinding(()->{
            if(chequeNo.getText().isEmpty())
                return false;
            else
                return true;
        },  chequeNo.textProperty());

        BooleanBinding isChequeAmountValid = Bindings.createBooleanBinding(()->{
            if(chequeAmount.getText().isEmpty())
                return false;
            else
                return true;
        },  chequeAmount.textProperty());

        BooleanBinding isResultTableRowSelected = Bindings.createBooleanBinding(()->{
            if(resultTable.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, resultTable.getSelectionModel().selectedItemProperty());

        //search lr button
        btnSearchLr.disableProperty().bind(isLrNoValid.not());

        //search invoice button
        btnSearchInvoice.disableProperty().bind(isInvoiceNoValid.not());

        //search invoice for update button
        btnSearchInvUpdate.disableProperty().bind(isInvNoToUpdateValid.not());

        //delete record button
        btnDelete.disableProperty().bind(isResultTableRowSelected.not());

        //update button
        btnUpdate.disableProperty().bind(isPayDateValid.not().or(isChequeNoValid.not().or(isChequeAmountValid.not())));
    }

    private void setupLrTable(){
        TableColumn lrNo = new TableColumn("LR No.");
        TableColumn consignor = new TableColumn("Consignor");
        TableColumn date = new TableColumn("Date");
        TableColumn destinationFrom = new TableColumn("From");
        TableColumn destinationTo = new TableColumn("To");
        TableColumn vehicleNo = new TableColumn("Vehicle No.");
        TableColumn weight = new TableColumn("Weight");
        TableColumn packageQuantity = new TableColumn("Pkg. Quantity");
        TableColumn packageType = new TableColumn("Package");
        TableColumn invoiceNo = new TableColumn("Invoice No.");

        resultTable.getColumns().addAll(lrNo, date, consignor, destinationFrom, destinationTo, vehicleNo, weight,
                packageQuantity, packageType, invoiceNo);

        lrNo.setCellValueFactory(new PropertyValueFactory<LrModel, Integer>("lrNo"));
        consignor.setCellValueFactory(new PropertyValueFactory<LrModel, String>("consignor"));
        date.setCellValueFactory(new PropertyValueFactory<LrModel, Date>("date"));
        destinationFrom.setCellValueFactory(new PropertyValueFactory<LrModel, String>("destinationFrom"));
        destinationTo.setCellValueFactory(new PropertyValueFactory<LrModel, String>("destinationTo"));
        vehicleNo.setCellValueFactory(new PropertyValueFactory<LrModel, String>("vehicleNo"));
        weight.setCellValueFactory(new PropertyValueFactory<LrModel, String>("weight"));
        packageQuantity.setCellValueFactory(new PropertyValueFactory<LrModel, Integer>("packageQuantity"));
        packageType.setCellValueFactory(new PropertyValueFactory<LrModel, String>("packageType"));
        invoiceNo.setCellValueFactory(new PropertyValueFactory<LrModel, String>("invoiceNo"));
    }

    private void setupInvoiceTable(){
        TableColumn billNo = new TableColumn("Bill No.");
        TableColumn lrNo = new TableColumn("LR No.");
        TableColumn billDate = new TableColumn("Bill Date");
        TableColumn consignor = new TableColumn("Consignor");
        TableColumn destinationFrom = new TableColumn("From");
        TableColumn destinationTo = new TableColumn("To");
        TableColumn vehicleNo = new TableColumn("Vehicle No.");
        TableColumn weight = new TableColumn("Weight");
        TableColumn packageQuantity = new TableColumn("Pkg. Quantity");
        TableColumn packageType = new TableColumn("Package");
        TableColumn invoiceNo =new TableColumn("Invoice No.");

        resultTable.getColumns().addAll(billNo, billDate, lrNo, consignor, destinationFrom, destinationTo,
                vehicleNo, weight,packageQuantity, packageType, invoiceNo);

        billNo.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("billNo"));
        lrNo.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, Integer>("lrNo"));
        billDate.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, Date>("billDate"));
        consignor.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("consignor"));
        destinationFrom.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("destinationFrom"));
        destinationTo.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("destinationTo"));
        vehicleNo.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("vehicleNo"));
        weight.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("weight"));
        packageQuantity.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, Integer>("packageQuantity"));
        packageType.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("packageType"));
        invoiceNo.setCellValueFactory(new PropertyValueFactory<InvoiceFModel, String>("invoiceNo"));
    }

    public void onClickSearchLrRecord(MouseEvent mouseEvent){
        lrList = lrDB.getDataByLrNo(Integer.parseInt(lrNo.getText()));
        clearResultTable();
        setupLrTable();
        resultTable.setItems(lrList);
        int listSize = lrList.size();
        lblResult.setText(listSize+" Results Found");
    }

    public void onClickSearchInvRecord(MouseEvent mouseEvent){
        invoiceList = invoiceDB.getInvoiceDetailsByBillNo(invoiceNo.getText());
        clearResultTable();
        setupInvoiceTable();
        resultTable.setItems(invoiceList);
        int listSize = invoiceList.size();
        lblResult.setText(listSize+" Results Found");
    }

    public void onClickBtnInvToUpdate(MouseEvent mouseEvent){

    }

    public void onClickDelete(MouseEvent mouseEvent){
        LrModel lrData = new LrModel("", null, 0, "", "", "", "",
                0, "", "");
        InvoiceFModel invoiceData = new InvoiceFModel("", 0, null, null, "", "",
                "", "", "", 0, "", "", 0, "");

        //if the search result contains lr data
        if(resultTable.getSelectionModel().getSelectedItem().getClass().isInstance(lrData)){
            //get the selected row data
            lrData = (LrModel) resultTable.getSelectionModel().getSelectedItem();
            //delete the record form table
            boolean result = lrDB.deleteLrRecord(lrData.getLrNo());

            //display alert
            if(result){
                windowManager.createAlert("Success", "Lr Record With LR No "+lrData.getLrNo()+" Deleted Successfully!",
                        Alert.AlertType.INFORMATION, manageRecordsPane);

                //set lr no to null
                lrNo.setText("");
                //clear table
                clearResultTable();
            }else{
                windowManager.createAlert("Error", "Error While Deleting Record",
                        Alert.AlertType.ERROR, manageRecordsPane);
                //set lr no to null
                lrNo.setText("");
                //clear table
                clearResultTable();
            }
        }

        //if search result contains invoice data
        if(resultTable.getSelectionModel().getSelectedItem().getClass().isInstance(invoiceData)){
            invoiceData = (InvoiceFModel) resultTable.getSelectionModel().getSelectedItem();
            boolean result = invoiceDB.deleteInvoiceRecord(invoiceData.getBillNo());
            if(result){
                windowManager.createAlert("Success", "Invoice Record With Invoice No "
                                +invoiceData.getBillNo()+" Deleted Successfully!",
                        Alert.AlertType.INFORMATION, manageRecordsPane);

                //set invoice no to null
                invoiceNo.setText("");
                //clear result table
                clearResultTable();
            }else{
                windowManager.createAlert("Error", "Error While Deleting Record",
                        Alert.AlertType.ERROR, manageRecordsPane);
                //set invoice no to null
                invoiceNo.setText("");
                //clear result table
                clearResultTable();
            }
        }
    }

    private void clearResultTable(){
        resultTable.getItems().clear();
        resultTable.getColumns().clear();
    }
}
