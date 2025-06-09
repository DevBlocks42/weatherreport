package com.weatherreport.model;

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
    
    public Forecast(Location location, List<Float> temperatures, List<Float> apparent_temperatures, List<Integer> precipitation_probability, List<Float> rain, List<Integer> cloud_cover, List<Float> wind_speed_10m, List<Integer> weather_code) {
        this.location = location;
        this.temperatures = temperatures;
        this.apparent_temperatures = apparent_temperatures;
        this.precipitation_probability = precipitation_probability;
        this.rain = rain;
        this.cloud_cover = cloud_cover;
        this.wind_speed_10m = wind_speed_10m;
        this.weather_code = weather_code;
    }
    
    public Forecast() {
    }
    
    public List<Float> getTemperatures() {
        return this.temperatures;
    }
    
}
