package com.weatherreport.model;

import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Classe représentant la météo d'une heure
 * @author DevBlocks42 
 */
public class ForecastRow {
    /**
     * Heure
     */
    private ObjectProperty<LocalTime> hour;
    /**
     * Température
     */
    private ObjectProperty<Float> temperature;
    /**
     * Ressenti de température
     */
    private ObjectProperty<Float> apparent_temperature;
    /**
     * Probabilité de pluie
     */
    private ObjectProperty<Integer> precipitation_probability;
    /**
     * Précipitations (en mm)
     */
    private ObjectProperty<Float> rain;
    /**
     * Couverture nuageuse
     */
    private ObjectProperty<Integer> cloud_cover;
    /**
     * Vitesse du vent à 10 mètres d'altitude
     */
    private ObjectProperty<Float> wind_speed_10m;
    /**
     * Code météo (description, symbole)
     */
    private ObjectProperty<Integer> weather_code;
    /**
     * Constructeur de l'objet
     * @param LocalTime time
     * @param Float temperature
     * @param Float apparent_temperature
     * @param Integer precipitation_probability
     * @param Float rain
     * @param Integer cloud_cover
     * @param Float wind_speed_10m
     * @param Integer weather_code 
     */
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
    /**
     * Retourne l'heure 
     * @return ObjectProperty<LocalTime> heure
     */
    public ObjectProperty<LocalTime> getHour() {
        return this.hour;
    }
    /**
     * Retourne la température
     * @return  ObjectProperty<Float> temperature
     */
    public ObjectProperty<Float> getTemperature() {
        return this.temperature;
    }
    /**
     * Retourne le ressenti de température
     * @return ObjectProperty<Float> apparent_temperature
     */
    public ObjectProperty<Float> getApparentTemperature() {
        return this.apparent_temperature;
    }
    /**
     * Retourne la probabilité de pluie
     * @return ObjectProperty<Integer> precipitation_probability
     */
    public ObjectProperty<Integer> getPrecipitationProbability() {
        return this.precipitation_probability;
    }
    /**
     * Retourne les précipitations
     * @return ObjectProperty<Float> rain
     */
    public ObjectProperty<Float> getRain() {
        return this.rain;
    }
    /**
     * Retourne la couverture nuageuse 
     * @return ObjectProperty<Integer> cloud_cover
     */
    public ObjectProperty<Integer> getCloudCover() {
        return this.cloud_cover;
    }
    /**
     * Retourne la vitesse du vent
     * @return ObjectProperty<Float> wind_speed_10m
     */
    public ObjectProperty<Float> getWindSpeed10m() {
        return wind_speed_10m;
    }
    /**
     * Retourne le code météo
     * @return ObjectProperty<Integer> weather_code
     */
    public ObjectProperty<Integer> getWeatherCode() {
        return this.weather_code;
    }   
}