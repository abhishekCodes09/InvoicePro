package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.database.controllers.UserDB;
import com.ghostCoder.invoicePro.models.UserModel;
import com.ghostCoder.invoicePro.security.PasswordHash;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

public class ChangePassword implements Initializable {

    @FXML
    private Label lblSecurityQue;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField answer;

    @FXML
    private AnchorPane changePasswordPane;

    @FXML
    private Button btnChangePassword;

    UserDB userDB = new UserDB();
    PasswordHash passwordHash = new PasswordHash();
    PasswordHash securityQueHash = new PasswordHash();
    PasswordHash newPasswordHash = new PasswordHash();
    WindowManager windowManager = new WindowManager();

    UserModel user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBooleanBinding();
        user = userDB.getUserData();
        lblSecurityQue.setText(user.getSecurityQue());
    }

    public void onClickChangePassword(MouseEvent mouseEvent){
        try {
            if(user.getPassword().equals(passwordHash.getHash(oldPassword.getText(), user.getSalt())) &&
                    user.getSecurityAns().equals(securityQueHash.getHash(answer.getText(), user.getSalt()))){

                String newHashedPassword = newPasswordHash.getHash(newPassword.getText(), user.getSalt());
                userDB.changePassword(newHashedPassword);
                windowManager.createAlert("Success", "Password changed successfully",
                        Alert.AlertType.INFORMATION, changePasswordPane);
            }else{
                windowManager.createAlert("Error", "Incorrect Old Password Or Security Answer!",
                        Alert.AlertType.ERROR, changePasswordPane);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        oldPassword.clear();
        newPassword.clear();
        answer.clear();
    }

    private void createBooleanBinding(){
        BooleanBinding oldPasswordValid = Bindings.createBooleanBinding(()->{
            if(oldPassword.getText().isEmpty())
                return false;
            else
                return true;
        }, oldPassword.textProperty());

        BooleanBinding newPasswordValid = Bindings.createBooleanBinding(()->{
            if(newPassword.getText().isEmpty())
                return false;
            else
                return true;
        }, newPassword.textProperty());

        BooleanBinding answerValid = Bindings.createBooleanBinding(()->{
            if(answer.getText().isEmpty())
                return false;
            else
                return true;

        }, answer.textProperty());

        btnChangePassword.disableProperty().bind(oldPasswordValid.not().or(newPasswordValid.not().or(answerValid.not())));
    }
}
