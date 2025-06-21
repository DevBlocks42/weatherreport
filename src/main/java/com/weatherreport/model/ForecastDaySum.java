package com.weatherreport.model;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Classe de représentation de la météo globale du jour
 * @author debvblocks42
 */
public class ForecastDaySum {
    private Location location = null;
    private int weather_code;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private Float maxTemp;
    private Float minTemp;
    
    public ForecastDaySum(Location location, int weather_code, LocalDateTime sunrise, LocalDateTime sunset, Float maxTemp, Float minTemp) {
        this.location = location;
        this.weather_code = weather_code;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
    
    public int getWeatherCode() {
        return weather_code;
    }
    
    public LocalDateTime getSunrise() {
        return sunrise;
    }
    
    public LocalDateTime getSunset() {
        return sunset;
    }
    
    public Float getMaxTemp() {
        return maxTemp;
    }
    
    public Float getMinTemp() {
        return minTemp;
    }
    
    public Location getLocation() {
        return location;
    }
}
