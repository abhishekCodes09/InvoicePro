package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SettingsCtrl {

    //border pane reference
    @FXML
    public BorderPane borderPane;

    //window manager object
    WindowManager windowManager = new WindowManager();


    //menu button handlers
    //manage consignors
    public void onClickManageConsignors(MouseEvent mouseEvent){
        AnchorPane homeView = windowManager.getView("manage-consignors");
        borderPane.setCenter(homeView);
    }

    //material type list
    public void onClickMaterialTypeList(MouseEvent mouseEvent){
        AnchorPane materialTypeView = windowManager.getView("material-type-list");
        borderPane.setCenter(materialTypeView);
    }

    //amount rate list
    public void onClickAmountRateList(MouseEvent mouseEvent){
        AnchorPane amountRateView = windowManager.getView("amount-rate-list");
        borderPane.setCenter(amountRateView);
    }

    //vehicle type
    public void onClickVehicleType(MouseEvent mouseEvent){
        AnchorPane vehicleTypeView = windowManager.getView("vehicle-type");
        borderPane.setCenter(vehicleTypeView);
    }

    //additional charges
    public void onClickAdditionalCharges(MouseEvent mouseEvent){
        AnchorPane additionalChargeView = windowManager.getView("additional-charges");
        borderPane.setCenter(additionalChargeView);
    }

    //change password
    public void onClickChangePassword(MouseEvent mouseEvent){
        AnchorPane changePasswordView = windowManager.getView("change-password");
        borderPane.setCenter(changePasswordView);
    }
}
