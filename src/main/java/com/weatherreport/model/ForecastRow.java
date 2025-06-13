package com.weatherreport.model;

import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Classe représentant la météo d'une heure
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class ForecastRow {
    private ObjectProperty<LocalTime> hour;
    private ObjectProperty<Float> temperature;
    private ObjectProperty<Float> apparent_temperature;
    private ObjectProperty<Integer> precipitation_probability;
    private ObjectProperty<Float> rain;
    private ObjectProperty<Integer> cloud_cover;
    private ObjectProperty<Float> wind_speed_10m;
    private ObjectProperty<Integer> weather_code;
    
    public ForecastRow(LocalTime time, Float temperature, Float apparent_temperature, Integer precipitation_probability, Float rain, Integer cloud_cover, Float wind_speed_10m, Integer weather_code) {
        this.hour = new SimpleObjectProperty<>(time);
        this.temperature = new SimpleObjectProperty(temperature);
        this.apparent_temperature = new SimpleObjectProperty(apparent_temperature);
        this.precipitation_probability = new SimpleObjectProperty(precipitation_probability);
        this.rain = new SimpleObjectProperty(rain);
        this.cloud_cover = new SimpleObjectProperty(cloud_cover);
        this.wind_speed_10m = new SimpleObjectProperty(wind_speed_10m);
        this.weather_code = new SimpleObjectProperty(weather_code);
    }
    
    public ObjectProperty<LocalTime> getHour() {
        return this.hour;
    }
    
    public ObjectProperty<Float> getTemperature() {
        return this.temperature;
    }
    
    public ObjectProperty<Float> getApparentTemperature() {
        return this.apparent_temperature;
    }
    
    public ObjectProperty<Integer> getPrecipitationProbability() {
        return this.precipitation_probability;
    }
    
    public ObjectProperty<Float> getRain() {
        return this.rain;
    }
    
    public ObjectProperty<Integer> getCloudCover() {
        return this.cloud_cover;
    }
    
    public ObjectProperty<Float> getWindSpeed10m() {
        return wind_speed_10m;
    }
    
    public ObjectProperty<Integer> getWeatherCode() {
        return this.weather_code;
    }
            
}
