package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.UserDB;
import com.ghostCoder.invoicePro.models.UserModel;
import com.ghostCoder.invoicePro.security.PasswordHash;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

public class Login implements Initializable {

    //window manager object
    WindowManager windowManager = new WindowManager();

    PasswordHash passwordHash = new PasswordHash();

    UserDB userDB = new UserDB();

    UserModel user;

    //controls
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private AnchorPane loginPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = userDB.getUserData();
    }

    public void onClickLogin(MouseEvent mouseEvent){
        String hashedPass = null;
        try {
            passwordHash.setHashedPassword(user.getPassword());
            hashedPass = passwordHash.getHash(password.getText(), user.getSalt());
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(username.getText().equals(user.getUsername()) && passwordHash.comparePassword(hashedPass)){
            try {
                windowManager.setScene("app-home", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            windowManager.createAlert("Login Error", "Invalid Username Or Password!",
                    Alert.AlertType.ERROR, loginPane);
        }
    }
}
