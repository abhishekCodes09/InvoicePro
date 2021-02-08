package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import com.ghostCoder.invoicePro.config.AppConfig;
import com.ghostCoder.invoicePro.database.InitTables;
import com.ghostCoder.invoicePro.database.controllers.UserDB;
import com.ghostCoder.invoicePro.models.UserModel;
import com.ghostCoder.invoicePro.security.PasswordHash;
import com.ghostCoder.invoicePro.validator.FormValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;


public class UserDetailsFromCtrl implements Initializable {
    //window manager object
    WindowManager windowManager = new WindowManager();

    //form validator object
    FormValidator formValidator = new FormValidator();

    PasswordHash passwordHash = new PasswordHash();
    PasswordHash securityAnsHash = new PasswordHash();
    String hashedSecurityAns;

    UserDB userDB = new UserDB();

    AppConfig appConfig = new AppConfig();

    InitTables initTables = new InitTables();

    //comboBox
    @FXML
    public ComboBox<String> city;

    @FXML
    public ComboBox<String> securityQue;

    //image view
    @FXML
    public ImageView imageView;

    @FXML
    public TextField securityAns, username, transportName, addressL1, addressL2, mobileNo, telephoneNo, emailId,
            aboutTransport, gstNo, pinCode;

    @FXML
    public PasswordField password;

    @FXML
    public Button btnCreateAccount;

    @FXML
    public AnchorPane userDataFormPane;

    //image file path
    String imagePath;
    boolean logoAdded = false;

    //cities list
    ObservableList<String> citiesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize all the tables
        initTables.createTables();

        //add validators to the form fields
        mobileNo.setTextFormatter(formValidator.numericAndRangeValidator(10));
        telephoneNo.setTextFormatter(formValidator.numericAndRangeValidator(10));
        pinCode.setTextFormatter(formValidator.numericAndRangeValidator(6));
        aboutTransport.setTextFormatter(formValidator.rangeValidator(50));
        transportName.setTextFormatter(formValidator.rangeValidator(50));
        addressL1.setTextFormatter(formValidator.rangeValidator(29));
        addressL2.setTextFormatter(formValidator.rangeValidator(29));
        gstNo.setTextFormatter(formValidator.rangeValidator(15));
        username.setTextFormatter(formValidator.rangeValidator(25));
        securityAns.setTextFormatter(formValidator.rangeValidator(40));


        //read json array list of cities
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("cities-name-list.json")){
            //read json config file
            Object citiesObj = jsonParser.parse(reader);

            //store data in json array
            JSONArray cities = (JSONArray)citiesObj;
            citiesList = FXCollections.observableArrayList(cities);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        //set the cities in combo box
        city.setItems(citiesList);

        //set security Questions
        securityQue.setItems(securityQuestions());

        createBooleanBinding();
    }

    //select logo
    public void onClickAddLogo(MouseEvent mouseEvent){
        File file = windowManager.loadFile("Select Logo");
        Image image = new Image(file.toURI().toString());
        imagePath = file.getAbsolutePath();
        System.out.println(file.getAbsolutePath());
        imageView.setImage(image);
        logoAdded = true;
    }

    //create boolean bindings
    private void createBooleanBinding(){
        BooleanBinding usernameValid = Bindings.createBooleanBinding(()->{
            if(username.getText().isEmpty())
                return false;
            else
                return true;

        }, username.textProperty());

        BooleanBinding passwordValid = Bindings.createBooleanBinding(()->{
            if(password.getText().isEmpty())
                return false;
            else
                return true;
        }, password.textProperty());

        BooleanBinding transportNameValid = Bindings.createBooleanBinding(()->{
            if(transportName.getText().isEmpty())
                return false;
            else
                return true;
        }, transportName.textProperty());

        BooleanBinding addrL1Valid = Bindings.createBooleanBinding(()->{
            if(addressL1.getText().isEmpty())
                return false;
            else
                return true;
        }, addressL1.textProperty());

        BooleanBinding addrL2Valid = Bindings.createBooleanBinding(()->{
            if(addressL2.getText().isEmpty())
                return false;
            else
                return true;
        }, addressL2.textProperty());

        BooleanBinding mobileNoValid = Bindings.createBooleanBinding(()->{
            if(mobileNo.getText().length() < 10)
                return false;
            else
                return true;
        }, mobileNo.textProperty());

        BooleanBinding cityValid = Bindings.createBooleanBinding(()->{
            if(city.getSelectionModel().isEmpty())
                return false;
            else
                return true;

        }, city.getSelectionModel().selectedItemProperty());

        BooleanBinding telephoneNoValid = Bindings.createBooleanBinding(()->{
            if(telephoneNo.getText().length() < 10)
                return false;
            else
                return true;
        }, telephoneNo.textProperty());

        BooleanBinding emailValid = Bindings.createBooleanBinding(()->{
            if(emailId.getText().isEmpty())
                return false;
            else
                return true;
        }, emailId.textProperty());

        BooleanBinding aboutTransportValid = Bindings.createBooleanBinding(()->{
            if(aboutTransport.getText().isEmpty())
                return false;
            else
                return true;
        },  aboutTransport.textProperty());

        BooleanBinding gstNoValid = Bindings.createBooleanBinding(()->{
            if(gstNo.getText().isEmpty())
                return false;
            else
                return true;

        }, gstNo.textProperty());

        BooleanBinding pinCodeValid = Bindings.createBooleanBinding(()->{
            if(pinCode.getText().isEmpty())
                return false;
            else
                return true;

        }, pinCode.textProperty());

        //disable the create account btn if form is invalid
        btnCreateAccount.disableProperty().bind(usernameValid.not().or(passwordValid.not().or(transportNameValid.not()
        .or(addrL1Valid.not().or(addrL2Valid.not().or(cityValid.not().or(mobileNoValid.not().or(telephoneNoValid.not()
        .or(emailValid.not().or(aboutTransportValid.not().or(gstNoValid.not().or(pinCodeValid.not()))))))))))));
    }

    //security que list
    public ObservableList securityQuestions(){
        ObservableList<String> securityQueList = FXCollections.observableArrayList();
        securityQueList.addAll("What is your mother's maiden name?", "Where did you meet your spouse?",
                "What is your favorite food?", "Where is your favorite place to vacation?",
                "What Is your favorite book?");
        return securityQueList;

    }

    //create Account
    public void onClickCreateAccount(MouseEvent mouseEvent) throws IOException {
        if(!formValidator.emailValidator(emailId.getText())){
            windowManager.createAlert("Invalid Email!", "The Email You Provided Is Invalid, Please" +
                    "Enter A Valid Email!", Alert.AlertType.INFORMATION, userDataFormPane);
        }else if(!logoAdded){
            windowManager.createAlert("No Logo Set!", "Please Set A Logo!",
                    Alert.AlertType.INFORMATION, userDataFormPane);
        }
        else{
             //copy the logo file to application's directory
             try(FileInputStream fileInputStream = new FileInputStream(imagePath)){

             //fos to create new logo file in current directory
             FileOutputStream fileOutputStream = new FileOutputStream("transport_logo.jpg");
             //write to file
             int c;
             while((c=fileInputStream.read())!=-1){
             fileOutputStream.write(c);
             }

             }catch(IOException e){
             e.printStackTrace();
             }

             //user setup complete
            appConfig.writeToAppConfig("isUserSetupDone", "true");

            try {
                passwordHash.generateHash(password.getText());
                securityAnsHash.setSalt(passwordHash.getSalt());
                hashedSecurityAns = securityAnsHash.getHash(securityAns.getText(), securityAnsHash.getSalt());
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            //create user data object
            UserModel userModel = new UserModel(username.getText(), passwordHash.getHashedPassword(), passwordHash.getSalt(),
                    securityQue.getSelectionModel().getSelectedItem(), hashedSecurityAns, transportName.getText(),
                    addressL1.getText(), addressL2.getText(), city.getSelectionModel().getSelectedItem(), mobileNo.getText()
                    , telephoneNo.getText(), emailId.getText(), aboutTransport.getText(), gstNo.getText(), pinCode.getText());

            //save data to db
            userDB.insertData(userModel);

            windowManager.setScene("login", false);
        }
    }

}
