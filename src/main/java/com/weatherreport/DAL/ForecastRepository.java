package com.weatherreport.DAL;

import com.weatherreport.model.ForecastDay;
import com.weatherreport.model.Location;
import com.weatherreport.http.ApiClient;
import com.weatherreport.http.HttpEntityResponse;
import com.weatherreport.model.ForecastDaySum;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe responsable de l'accès aux données météorologiques du jour via l'API open-meteo
 * @author DevBlocks42
 */
public class ForecastRepository {
    /**
     * Instance d'ApiClient
     */
    private ApiClient apiClient;
    private final String forecastURI = "/forecast";
    /**
     * Constructeur
     * @param repository 
     */
    public ForecastRepository(Repository repository) {
        apiClient = repository.getApiClient();
    }
    
    /**
     * Retouurne un ForecastDay du lieu spécifié en paramètre
     * @param location
     * @return ForecastDay forecastDay
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
    /**
     * Retourne une liste de ForecastDaySum pour le lieu et entre la plage de dates saisies en paramètres
     * @param location
     * @param startDate
     * @param endDate
     * @return List<ForecastDaySum> sums
     */
    public List<ForecastDaySum> getForecastDaySum(Location location, LocalDate startDate, LocalDate endDate) {
        List<ForecastDaySum> sums = new ArrayList<>();
        try {
            HttpEntityResponse response = apiClient.sendGetRequest(apiClient.getOpenmeteoApiURL(), forecastURI + "?latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude() + "&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset&timezone=auto"); //&start_date=" + startDate.toString() /*+ "&end_date=" + endDate.toString()*/);
            String textResponse = response.getContent();
            JSONObject root = new JSONObject(textResponse);
            JSONObject daily = (JSONObject)root.get("daily");
            JSONArray dailyMinTempsArray = daily.optJSONArray("temperature_2m_min");
            JSONArray dailyMaxTempsArray = daily.optJSONArray("temperature_2m_max");
            JSONArray dailyWeatherCodesArray = daily.optJSONArray("weather_code");
            JSONArray dailySunsetsArray = daily.optJSONArray("sunset");
            JSONArray dailySunrisesArray = daily.optJSONArray("sunrise");
            String datePattern = "yyyy-MM-dd'T'HH:mm";
            for(int i = 0; i < dailyMinTempsArray.length(); i++) {
                
                LocalDateTime sunrise = LocalDateTime.parse(dailySunrisesArray.get(i).toString(), DateTimeFormatter.ofPattern(datePattern));
                LocalDateTime sunset = LocalDateTime.parse(dailySunsetsArray.get(i).toString(), DateTimeFormatter.ofPattern(datePattern));
                Float maxTemp = dailyMaxTempsArray.getFloat(i);
                Float minTemp = dailyMinTempsArray.getFloat(i);
                int weatherCode = dailyWeatherCodesArray.getInt(i);
                ForecastDaySum forecastDaySum = new ForecastDaySum(location, weatherCode, sunrise, sunset, maxTemp, minTemp);
                sums.add(forecastDaySum);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sums;
    }
}
