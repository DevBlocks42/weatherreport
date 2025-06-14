package com.weatherreport.DAL;

import com.weatherreport.model.ForecastDay;
import com.weatherreport.model.Location;
import com.weatherreport.http.ApiClient;
import com.weatherreport.http.HttpEntityResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe responsable de l'accès aux données météorologiques du jour via l'API open-meteo
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class ForecastRepository {
    
    private ApiClient apiClient;
    private final String forecastURI = "/forecast";
    
    public ForecastRepository(Repository repository) {
        apiClient = repository.getApiClient();
    }
    
    /**
     * Retouurne un ForecastDay du lieu spécifié en paramètre
     * @param location
     * @return un ForecastDay
     */
    public ForecastDay getCurrentForecast(Location location, LocalDate date) {
        ForecastDay forecast = new ForecastDay();
        try {
            System.out.println(date.toString());
            //&forecast_days=1
            HttpEntityResponse response = apiClient.sendGetRequest(apiClient.getOpenmeteoApiURL(), forecastURI + "?latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude() + "&hourly=temperature_2m,apparent_temperature,precipitation_probability,rain,cloud_cover,wind_speed_10m,weather_code&timezone=auto&start_date=" + date.toString() + "&end_date=" + date.toString());
            String textResponse = response.getContent();
            JSONObject root = new JSONObject(textResponse);
            JSONObject temp = (JSONObject)root.get("hourly");
            JSONArray apparentTempArray = temp.optJSONArray("apparent_temperature");
            JSONArray tempArray = temp.optJSONArray("temperature_2m");
            JSONArray precipitationProbArray = temp.optJSONArray("precipitation_probability");
            JSONArray rainArray = temp.optJSONArray("rain");
            JSONArray cloudCoverArray = temp.optJSONArray("cloud_cover");
            JSONArray windSpeedArray = temp.optJSONArray("wind_speed_10m");
            JSONArray weatherCodeArray = temp.optJSONArray("weather_code");
            List<Float> temperatures = new ArrayList<>();
            List<Float> apparent_temperatures = new ArrayList<>();
            List<Integer> precipitation_probability = new ArrayList<>();
            List<Float> rain = new ArrayList<>();
            List<Integer> cloud_cover = new ArrayList<>();
            List<Float> wind_speed_10m = new ArrayList<>();
            List<Integer> weather_code = new ArrayList<>();
            for(int i = 0; i < tempArray.length(); i++) {
               temperatures.add(tempArray.getFloat(i));
               apparent_temperatures.add(apparentTempArray.getFloat(i));
               precipitation_probability.add(precipitationProbArray.getInt(i));
               rain.add(rainArray.getFloat(i));
               cloud_cover.add(cloudCoverArray.getInt(i));
               wind_speed_10m.add(windSpeedArray.getFloat(i));
               weather_code.add(weatherCodeArray.getInt(i));
            }
            forecast = new ForecastDay(location, temperatures, apparent_temperatures, precipitation_probability, rain, cloud_cover, wind_speed_10m, weather_code);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return forecast;
    }
}
