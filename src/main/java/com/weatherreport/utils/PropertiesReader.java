package com.weatherreport.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static final String FILE_PATH = "app.properties";
    private static Properties PROPERTIES;
    
    static {
        try {
            PROPERTIES = new Properties();
            PROPERTIES.load(new FileInputStream(FILE_PATH));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String getProperty(String name) {
        return PROPERTIES.getProperty(name);
    }
}
