package com.weatherreport.model;

import com.weatherreport.utils.MathUtils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de représentation de la météo en temps réel
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class Forecast {
    private Location location = null;
    private List<Float> temperatures;
    private List<Float> apparent_temperatures;
    private List<Integer> precipitation_probability;
    private List<Float> rain;
    private List<Integer> cloud_cover;
    private List<Float> wind_speed_10m;
    private List<Integer> weather_code;
    private List<LocalTime> dailyClock = new ArrayList<>();
    
    public Forecast(Location location, List<Float> temperatures, List<Float> apparent_temperatures, List<Integer> precipitation_probability, List<Float> rain, List<Integer> cloud_cover, List<Float> wind_speed_10m, List<Integer> weather_code) {
        this.location = location;
        this.temperatures = temperatures;
        this.apparent_temperatures = apparent_temperatures;
        this.precipitation_probability = precipitation_probability;
        this.rain = rain;
        this.cloud_cover = cloud_cover;
        this.wind_speed_10m = wind_speed_10m;
        this.weather_code = weather_code;
        for(int i = 0; i < 24; i++) {
            LocalTime tmpTime = LocalTime.MIDNIGHT;
            tmpTime = tmpTime.plusHours(i);
            dailyClock.add(tmpTime);
        }
    }
    
    public Forecast() {
    }
    
    public List<Float> getTemperatures() {
        return this.temperatures;
    }
    
    public List<Float> getApparentTemperatures() {
        return this.apparent_temperatures;
    }
    
    public List<Integer> getPrecipitationProbs() {
        return this.precipitation_probability;
    }
    
    public List<Float> getRain() {
        return this.rain;
    }
    
    public List<Integer> getCloudCover() {
        return this.cloud_cover;
    }
    
    public List<Float> getWindSpeed10m() {
        return this.wind_speed_10m;
    }
    
    public List<Integer> getWeatherCodes() {
        return this.weather_code;
    } 
   
    
    public Float getMaxTemperature() {
        return MathUtils.getMaxOfFloatList(this.temperatures);
    }
    
    public Float getMaxApparentTemperature() {
        return MathUtils.getMaxOfFloatList(this.apparent_temperatures);
    }
    
    public Integer getMaxPrecipitationProbs() {
        return MathUtils.getMaxOfIntegerList(this.precipitation_probability);
    }
    
    public Float getMaxRain() {
        return MathUtils.getMaxOfFloatList(this.rain);  
    }
    
    public Integer getMaxCloudCover() {
        return MathUtils.getMaxOfIntegerList(this.cloud_cover);  
    }
    
    public Float getMaxWindSpeed() {
        return MathUtils.getMaxOfFloatList(this.wind_speed_10m);  
    }
    
    public List<LocalTime> getDailyClock() {
        return this.dailyClock;
    }
}
