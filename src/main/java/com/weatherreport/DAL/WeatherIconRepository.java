package com.weatherreport.DAL;

import com.weatherreport.http.ApiClient;
import com.weatherreport.http.HttpBytesEntityResponse;
import com.weatherreport.model.WeatherIcon;
import com.weatherreport.utils.WeatherCodeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * Classe responsable de l'accès aux descriptions et icones météo via l'API openweathermap
 * @author DevBlocks42 
 */
public class WeatherIconRepository {
    private ApiClient apiClient;
    private final String forecastURI = "/forecast";
    
    public WeatherIconRepository(Repository repository) {
        apiClient = repository.getApiClient();
    }
    
    /**
     * Retourne l'objet WeatherIcon correspondant au code spécifié
     * @param weather_code
     * @return un WeatherIcon ou null
     */
    public WeatherIcon getWeatherIcon(int weather_code) {
        String[] codeDatas = WeatherCodeConverter.getWMOCode(weather_code);
        String WMOCode = codeDatas[0];
        String description = codeDatas[1];
        HttpBytesEntityResponse response;
        WeatherIcon weatherIcon = null;
        try {
            response = apiClient.sendGetRequestToBytes(apiClient.getOpenweatherApiURL(), "/" + WMOCode);
            byte[] bytes = response.getContent();
            String tmpPath = "";
            String osName = System.getProperty("os.name").toLowerCase();
            if(osName.contains("win")) {
                tmpPath = System.getenv("TEMP") + WMOCode;
            } else {
                tmpPath = "/tmp/wmoicon_" + WMOCode;
            }
            File file = new File(tmpPath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
            Image image = new Image(file.toURI().toString());
            weatherIcon = new WeatherIcon(weather_code, description, image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return weatherIcon;    
    }
}