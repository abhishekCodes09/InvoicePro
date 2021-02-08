package com.ghostCoder.invoicePro.config;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    private final Properties properties = new Properties();
    private FileReader fileReader = null;

    public AppConfig(){

    }

    public void writeToAppConfig(String key, String value){
        properties.setProperty(key, value);
        try {
            properties.store(new FileWriter("application.properties"), "application properties file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserSetupDone(){
        try {
             fileReader = new FileReader("application.properties");
             properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = properties.getProperty("isUserSetupDone");
        return Boolean.valueOf(value);
    }

}
