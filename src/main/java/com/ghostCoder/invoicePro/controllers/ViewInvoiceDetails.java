package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.AdditionalChargesDB;
import com.ghostCoder.invoicePro.database.controllers.ConsignorDB;
import com.ghostCoder.invoicePro.database.controllers.InvoiceDB;
import com.ghostCoder.invoicePro.database.controllers.UserDB;
import com.ghostCoder.invoicePro.models.*;
import com.ghostCoder.invoicePro.util.AmountToWords;
import com.ghostCoder.invoicePro.util.BillNoHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewInvoiceDetails implements Initializable {

    @FXML
    private JRViewerFX jrViewer;

    @FXML
    private AnchorPane viewInvoiceReportPane;

    JasperPrint jasperPrint;
    JasperReport jasperReport;

    BillNoHolder billNoHolder = BillNoHolder.getInstance();
    String billNo;
    int totalAmount_INT;
    String totalAmount;
    String tPaisa = "00";
    WindowManager windowManager = new WindowManager();

    UserModel userData;
    ObservableList<InvoiceFModel> invoiceData = FXCollections.observableArrayList();
    ObservableList<AdditionalChargesModel> additionalChargesData = FXCollections.observableArrayList();
    ObservableList<InvoiceReportModel> invoiceReportData = FXCollections.observableArrayList();
    ObservableList<InvoiceAddChargeModel> invoiceAddChargeData = FXCollections.observableArrayList();

    ConsignorModel consignor;

    InvoiceDB invoiceDB = new InvoiceDB();
    ConsignorDB consignorDB = new ConsignorDB();
    AdditionalChargesDB additionalChargesDB = new AdditionalChargesDB();
    UserDB userDB = new UserDB();

    //map to hold report parameters
    Map<String, Object> parameters = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get bill number for which the invoice is to be generated
        billNo = billNoHolder.getBillNo();

        fetchInvoiceData();
        setupData();
        showReport();
    }

    public void onClickSave(MouseEvent mouseEvent){
        try {
            String path = windowManager.directoryChoose("secondary");
            String file = path+"/"+"BillNo-"+billNo+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, file);
            windowManager.createAlert("Success", "Invoice saved to location: "+path+" Successfully!",
                    Alert.AlertType.INFORMATION, viewInvoiceReportPane);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void onClickPrint(MouseEvent mouseEvent){
        try {
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void fetchInvoiceData(){
        invoiceData = invoiceDB.getInvoiceDetailsByBillNo(billNo);
        consignor = consignorDB.getConsignorDetails(invoiceData.get(0).getConsignor());
        additionalChargesData = additionalChargesDB.getAdditionalCharge(invoiceData.get(0).getBillNo());
        userData = userDB.getUserData();

        for(InvoiceFModel invoice: invoiceData){
            invoiceReportData.add(new InvoiceReportModel(invoice.getLrNo(), invoice.getLrDate().toString(),
                    invoice.getVehicleNo(), invoice.getWeight(), invoice.getPackageQuantity() + " "+
                    invoice.getPackageType(), invoice.getInvoiceNo(), invoice.getBillAmount(), invoice.getBillRate(),
                    "00"));
        }

        for(AdditionalChargesModel addCharge: additionalChargesData){
            if(addCharge.getAdditionalInfo().isEmpty()){
                invoiceAddChargeData.add(new InvoiceAddChargeModel(addCharge.getChargeName()+
                        addCharge.getAdditionalInfo(), addCharge.getAmount(), addCharge.getRate(), "00"));
            }else {
                invoiceAddChargeData.add(new InvoiceAddChargeModel(addCharge.getChargeName() + "\n" +
                        addCharge.getAdditionalInfo(), addCharge.getAmount(), addCharge.getRate(), "00"));
            }
        }
    }

    public void setupData(){
        //calculate total amount and compute amount in words
        totalAmount_INT += invoiceReportData.get(0).getAmount();

        for(InvoiceAddChargeModel addCharge: invoiceAddChargeData){
            totalAmount_INT+=addCharge.getAmount();
        }

        totalAmount = AmountToWords.convert(String.valueOf(totalAmount_INT));

        //destination to
        int destCount = 1;
        StringBuilder destinationTo = new StringBuilder();
        for (InvoiceFModel invoice: invoiceData){
            if(destCount == 1){
                destinationTo.append(invoice.getDestinationTo());
            }else{
                destinationTo.append(", "+invoice.getDestinationTo());
            }
        }
        //data sources for the table
        JRBeanCollectionDataSource invoiceDataBean = new JRBeanCollectionDataSource(invoiceReportData);
        JRBeanCollectionDataSource addChargesBean = new JRBeanCollectionDataSource(invoiceAddChargeData);

        parameters.put("transportName", userData.getTransportName());
        parameters.put("additionalInfo", userData.getAboutTransport());
        parameters.put("address", userData.getAddress1()+" "+userData.getAddress2()+" "+userData.getCity()+"-"+userData.getPinCode());
        parameters.put("contactNo", userData.getMobileNo());
        parameters.put("emailId", userData.getEmailId());
        parameters.put("userGstNo", userData.getGstNo());

        parameters.put("cname", consignor.getCname());
        parameters.put("cAddressL1", consignor.getAddressL1());
        parameters.put("cAddressL2", consignor.getAddressL2()+consignor.getCity());
        parameters.put("cGstNo", consignor.getGstNo());

        parameters.put("billNo", invoiceData.get(0).getBillNo());
        parameters.put("billDate", invoiceData.get(0).getBillDate().toString());

        parameters.put("destinationFrom", invoiceData.get(0).getDestinationFrom());
        parameters.put("destinationTo", destinationTo.toString());

        parameters.put("InvoiceBean", invoiceDataBean);
        parameters.put("AdditionalChargesBean", addChargesBean);

        parameters.put("amountInWords", totalAmount);
        parameters.put("tPaisa", tPaisa);
        parameters.put("totalAmount", String.valueOf(totalAmount_INT));

        parameters.put("logo", new File("transport_logo.jpg").toURI().toString());
        //System.out.println("amount int: "+totalAmount_INT);
        //System.out.println("amount in words: "+totalAmount);
    }

    private void showReport(){
        InputStream input = null;
        try {
            input = new FileInputStream("invoice.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(input);

            jasperReport = JasperCompileManager.compileReport(jasperDesign);

            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            jrViewer.setReport(jasperPrint);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
    }
}
