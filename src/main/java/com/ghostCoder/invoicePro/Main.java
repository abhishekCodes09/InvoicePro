package com.ghostCoder.invoicePro;

import com.ghostCoder.invoicePro.config.AppConfig;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    AppConfig appConfig = new AppConfig();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //create window manager object
        WindowManager windowManager = new WindowManager(primaryStage);

        //check if the user setup is done
        if(appConfig.isUserSetupDone()){
            windowManager.createWindow("login", false);
        }else{
            windowManager.createWindow("welcome", false);
        }

    }
}
