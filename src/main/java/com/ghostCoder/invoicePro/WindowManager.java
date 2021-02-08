package com.ghostCoder.invoicePro;

import com.ghostCoder.invoicePro.database.AppDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class WindowManager {
    private static Stage mainStage;
    private static final Stage secondaryStage = new Stage();
    AppDB appDB = new AppDB();

    //empty constructor
    public WindowManager() {
    }

    //this constructor is used by main method which provides it the primary stage
    public WindowManager(Stage mainStage) {
        WindowManager.mainStage = mainStage;
        secondaryStage.initOwner(mainStage);
        mainStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/assets/app_logo_light_icon.png"))));
        mainStage.setTitle("Invoice Pro");
        appDB.createConnection();
        WindowManager.mainStage.setOnCloseRequest(e->{
            e.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invoice Pro");
            alert.setHeaderText("Quit InvoicePro?");
            alert.setContentText("Are you sure you want to perform this action?");

            //set icon on alert window
            Stage alertWindow = (Stage) alert.getDialogPane().getScene().getWindow();
            alertWindow.getIcons().add(new Image(String.valueOf(getClass().getResource("/assets/app_logo_light_icon.png"))));

            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional) result).get() == ButtonType.OK) {
                appDB.closeConnection();
                WindowManager.mainStage.close();
            }
        });
    }

    //create a window
    public void createWindow(String fileName, boolean setResizable) throws IOException {
        //load the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + fileName + ".fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setResizable(setResizable);
        mainStage.show();
    }

    //set or change scene
    public void setScene(String fileName, boolean setResizable) throws IOException {

        //load the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + fileName + ".fxml"));
        Scene scene = new Scene(root);

        //close current stage
        mainStage.close();

        //set new scene
        mainStage.setScene(scene);
        mainStage.setResizable(setResizable);
        mainStage.show();
    }

    //file picker
    public File loadFile(String windowTitle){
        //create file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(windowTitle);

        //store the file
        File file = fileChooser.showOpenDialog(mainStage);
        return file;
    }

    public String directoryChoose(String window){
        DirectoryChooser directoryChooser = new DirectoryChooser();;
        File selectedDirectory;

        if(window.equals("primary")){
            selectedDirectory = directoryChooser.showDialog(mainStage);
        }else{
            selectedDirectory = directoryChooser.showDialog(secondaryStage);
        }
        return selectedDirectory.getAbsolutePath();
    }

    //get menu views
    public AnchorPane getView(String filename){
        AnchorPane view = null;
        try{
            URL fileUrl = getClass().getResource("/fxml/" + filename + ".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("Requested FXML file not found!");
            }
            view = new FXMLLoader().load(fileUrl);
        }catch(IOException e){
            System.out.println("No file with "+filename+" found, please check the filename.");
        }
        return view;
    }

    //create a secondary stage
    public void createSecondaryWindow(String filename, String windowTitle) {
        try {
            //load the fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + filename + ".fxml"));
            Scene scene = new Scene(root);

            //set scene and show
            secondaryStage.setScene(scene);

            secondaryStage.setTitle(windowTitle);
            secondaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/assets/app_logo_light_icon.png"))));
            secondaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
        WindowManager.secondaryStage.setOnCloseRequest(e->{
            closeSecondaryStage();
        });
    }

    //close secondary stage
    public void closeSecondaryStage(){
        secondaryStage.close();
    }


    //alert
    public void createAlert(String headerText, String contentText, Alert.AlertType type,
                                       AnchorPane anchorPane){

        //stage on which to show the alert
        Stage stage = (Stage)anchorPane.getScene().getWindow();

        //alert
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        //set alert header and content
        alert.getDialogPane().setHeaderText(headerText);
        alert.getDialogPane().setContentText(contentText);

        alert.showAndWait();
    }


}
