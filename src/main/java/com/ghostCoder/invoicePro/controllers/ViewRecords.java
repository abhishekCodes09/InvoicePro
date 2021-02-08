package com.ghostCoder.invoicePro.controllers;
import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.ConsignorDB;
import com.ghostCoder.invoicePro.database.controllers.InvoiceDB;
import com.ghostCoder.invoicePro.database.controllers.LrDB;
import com.ghostCoder.invoicePro.models.InvoiceFModel;
import com.ghostCoder.invoicePro.models.LrModel;
import com.ghostCoder.invoicePro.util.BillNoHolder;
import com.ghostCoder.invoicePro.validator.FormValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ViewRecords implements Initializable {
    @FXML
    private TextField lrNo, lrYear, invoiceNo, invYear;

    @FXML
    private ComboBox lrConsignor, invConsignor;

    @FXML
    private Button btnSearchByLr, btnLrSearchByConsignor, btnViewLr, btnSearchByInvNo, btnInvSearchByConsignor,
            btnViewInv;

    @FXML
    private TableView lrResultTable, invResultTable;

    @FXML
    private Label lblLrResult, lblInvResult;

    FormValidator formValidator = new FormValidator();

    ConsignorDB consignorDB = new ConsignorDB();
    LrDB lrDB = new LrDB();
    InvoiceDB invoiceDB = new InvoiceDB();
    WindowManager windowManager = new WindowManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();
        setupLrTable();
        setupInvoiceTable();

        //set consignors
        lrConsignor.setItems(consignorDB.getConsignorsList());
        invConsignor.setItems(consignorDB.getConsignorsList());

        lrNo.setTextFormatter(formValidator.numericValidator());
        lrYear.setTextFormatter(formValidator.numericAndRangeValidator(4));
        invYear.setTextFormatter(formValidator.numericAndRangeValidator(4));
    }

    private void createBooleanBinding(){
        BooleanBinding isLrNoValid = Bindings.createBooleanBinding(()->{
            if(lrNo.getText().isEmpty())
                return false;
            else
                return true;
        },  lrNo.textProperty());

        BooleanBinding isLrYearValid = Bindings.createBooleanBinding(()->{
            if(lrYear.getText().isEmpty() || lrYear.getText().length() < 4)
                return false;
            else
                return true;
        },  lrYear.textProperty());

        BooleanBinding isInvoiceNoValid = Bindings.createBooleanBinding(()->{
            if(invoiceNo.getText().isEmpty())
                return false;
            else
                return true;
        },  invoiceNo.textProperty());

        BooleanBinding isInvYearValid = Bindings.createBooleanBinding(()->{
            if(invYear.getText().isEmpty() || invYear.getText().length()<4)
                return false;
            else
                return true;
        },  invYear.textProperty());

        BooleanBinding isLrConsignorValid = Bindings.createBooleanBinding(()->{
            if(lrConsignor.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, lrConsignor.getSelectionModel().selectedItemProperty());

        BooleanBinding isInvConsignorValid = Bindings.createBooleanBinding(()->{
            if(invConsignor.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, invConsignor.getSelectionModel().selectedItemProperty());

        BooleanBinding isInvTableRowSelected = Bindings.createBooleanBinding(()->{
            if(invResultTable.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, invResultTable.getSelectionModel().selectedItemProperty());

        BooleanBinding isLrTableRowSelected = Bindings.createBooleanBinding(()->{
            if(lrResultTable.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, lrResultTable.getSelectionModel().selectedItemProperty());

        //bind button disable property
        btnSearchByLr.disableProperty().bind(isLrNoValid.not());
        btnSearchByInvNo.disableProperty().bind(isInvoiceNoValid.not());
        btnLrSearchByConsignor.disableProperty().bind(isLrConsignorValid.not().or(isLrYearValid.not()));
        btnInvSearchByConsignor.disableProperty().bind(isInvConsignorValid.not().or(isInvYearValid.not()));
        btnViewLr.disableProperty().bind(isLrTableRowSelected.not());
        btnViewInv.disableProperty().bind(isInvTableRowSelected.not());
    }

    //setup columns for lr result table
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

        lrResultTable.getColumns().addAll(lrNo, date, consignor, destinationFrom, destinationTo, vehicleNo, weight,
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

    //setup columns for invoice table
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

        invResultTable.getColumns().addAll(billNo, billDate, lrNo, consignor, destinationFrom, destinationTo,
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

    public void onClickSearchByLrNo(){
        ObservableList<LrModel> lrList = lrDB.getDataByLrNo(Integer.parseInt(lrNo.getText()));
        lrResultTable.setItems(lrList);
        int listSize = lrList.size();
        lblLrResult.setText(listSize+" Results Found");
    }

    public void onClickSearchLrByConsignor(){
        ObservableList<LrModel> lrList = lrDB.getDataByConsignor(lrConsignor.getSelectionModel().getSelectedItem().toString(),
                lrYear.getText());
        lrResultTable.setItems(lrList);
        int listSize = lrList.size();
        lblLrResult.setText(listSize+" Results Found");
    }

    public void onClickSearchByInvNo(){
        ObservableList<InvoiceFModel> invoiceList = invoiceDB.getInvoiceDetailsByBillNo(invoiceNo.getText());
        invResultTable.setItems(invoiceList);
        int listSize = invoiceList.size();
        lblInvResult.setText(listSize+" Results Found");
    }

    public void onClickSearchInvByConsignor(){
        ObservableList<InvoiceFModel> invoiceList =invoiceDB.getInvoiceDetailsByConsignor(invConsignor.getSelectionModel().
                        getSelectedItem().toString(),
                invYear.getText());
        invResultTable.setItems(invoiceList);
        int listSize = invoiceList.size();
        lblInvResult.setText(listSize+" Results Found");
    }

    public void onClickViewLr(){

    }

    public void onClickViewInvoice(){
        InvoiceFModel invoice = (InvoiceFModel) invResultTable.getSelectionModel().getSelectedItem();
        BillNoHolder billNoHolder = BillNoHolder.getInstance();
        billNoHolder.setBillNo(invoice.getBillNo());
        windowManager.createSecondaryWindow("view-invoice-report", "Invoice Pro - Invoice Details");
    }
}
