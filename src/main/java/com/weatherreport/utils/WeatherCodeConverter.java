package com.weatherreport.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

/**
 * Classe utilitaire permettant de convertir un weather_code retourné par open-meteo en code WMO
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class WeatherCodeConverter {
    private static final String weatherCodeFilePath = "src/main/resources/com/weatherreport/wmo_translated_fr.json";
    
    public static String[] getWMOCode(Integer weather_code) {
        String[] ret = new String[2];
        String wmoCode = "";
        File file = new File(weatherCodeFilePath);
        try {
            FileInputStream inputFile = new FileInputStream(file);
            String json = IOUtils.toString(inputFile, "UTF-8");
            JSONObject root = new JSONObject(json);
            JSONObject weatherObject = root.optJSONObject(weather_code.toString());
            JSONObject dayObject = weatherObject.optJSONObject("day");
            String url = dayObject.optString("image");
            String description = dayObject.optString("description");
            String[] code = url.split("/");
            ret[0] = code[code.length - 1];
            ret[1] = description;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ret;
    }
    
}
