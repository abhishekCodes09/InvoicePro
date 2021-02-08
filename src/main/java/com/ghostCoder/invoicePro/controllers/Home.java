package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.database.controllers.UserDB;
import com.ghostCoder.invoicePro.models.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private Label username, transportName;

    @FXML
    private ImageView transportLogo;

    UserDB userDB = new UserDB();

    UserModel userData;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userData = userDB.getUserData();
        username.setText(userData.getUsername());
        transportName.setText(userData.getTransportName());
        File image = new File("transport_logo.jpg");
        Image transport_logo = new Image(image.toURI().toString());
        transportLogo.setImage(transport_logo);
    }

}
