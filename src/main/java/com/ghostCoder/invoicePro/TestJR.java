package com.ghostCoder.invoicePro;

import com.ghostCoder.invoicePro.database.controllers.AdditionalChargesDB;
import com.ghostCoder.invoicePro.database.controllers.ConsignorDB;
import com.ghostCoder.invoicePro.database.controllers.InvoiceDB;
import com.ghostCoder.invoicePro.database.controllers.UserDB;
import com.ghostCoder.invoicePro.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class TestJR {
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

    public TestJR(String billNo){
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
                invoiceAddChargeData.add(new InvoiceAddChargeModel(addCharge.getChargeName()+"/n"+
                        addCharge.getAdditionalInfo(), addCharge.getAmount(), addCharge.getRate(), "00"));
            }
    }

    public void setupData(){
        //data sources for the table
        JRBeanCollectionDataSource invoiceDataBean = new JRBeanCollectionDataSource(invoiceReportData);
        JRBeanCollectionDataSource addChargesBean = new JRBeanCollectionDataSource(invoiceAddChargeData);

        parameters.put("transportName", userData.getTransportName());
        parameters.put("additionalInfo", userData.getAboutTransport());
        parameters.put("address", userData.getAddress1()+" "+userData.getAddress2());
        parameters.put("contactNo", userData.getMobileNo());
        parameters.put("emailId", userData.getEmailId());
        parameters.put("userGstNo", null);

        parameters.put("cname", consignor.getCname());
        parameters.put("cAddressL1", consignor.getAddressL1());
        parameters.put("cAddressL2", consignor.getAddressL2()+consignor.getCity());
        parameters.put("cGstNo", consignor.getGstNo());

        parameters.put("billNo", invoiceData.get(0).getBillNo());
        parameters.put("billDate", invoiceData.get(0).getBillDate().toString());

        parameters.put("destinationFrom", invoiceData.get(0).getDestinationFrom());
        parameters.put("destinationTo", invoiceData.get(0).getDestinationTo());

        parameters.put("InvoiceBean", invoiceDataBean);
        parameters.put("AdditionalChargesBean", addChargesBean);
    }

    public void showReport() throws URISyntaxException, FileNotFoundException, JRException {
        InputStream input = new FileInputStream("invoice.jrxml");

        JasperDesign jasperDesign = JRXmlLoader.load(input);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JasperViewer.viewReport(jasperPrint);
    }
}
