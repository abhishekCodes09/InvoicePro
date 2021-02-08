package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class AppHomeCtrl implements Initializable {

    //border pane reference
    @FXML
    public BorderPane borderPane;

    //window manager object
    WindowManager windowManager = new WindowManager();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane homeView = windowManager.getView("home");
        borderPane.setCenter(homeView);
    }

    //menu button handlers
    //home
    public void onClickHome(MouseEvent mouseEvent){
        AnchorPane homeView = windowManager.getView("home");
        borderPane.setCenter(homeView);
    }

    //create new lr record
    public void onClickCreateNewLR(MouseEvent mouseEvent){
        AnchorPane createNewLRView = windowManager.getView("create-LR");
        borderPane.setCenter(createNewLRView);
    }

    //create new invoice
    public void onClickCreateNewInvoice(MouseEvent mouseEvent){
        AnchorPane createNewInvoiceView = windowManager.getView("create-invoice");
        borderPane.setCenter(createNewInvoiceView);
    }

    //view records
    public void onClickViewRecords(MouseEvent mouseEvent){
        AnchorPane viewRecordsView = windowManager.getView("view-records");
        borderPane.setCenter(viewRecordsView);
    }

    //view records
    public void onClickManageRecords(MouseEvent mouseEvent){
        AnchorPane manageRecordsView = windowManager.getView("manage-records");
        borderPane.setCenter(manageRecordsView);
    }

    //settings
    public void onClickSettings(MouseEvent mouseEvent){
        AnchorPane settingsView = windowManager.getView("settings");
        borderPane.setCenter(settingsView);
    }

    public void onClickAbout(MouseEvent mouseEvent){
        AnchorPane settingsView = windowManager.getView("about");
        borderPane.setCenter(settingsView);
    }
}
