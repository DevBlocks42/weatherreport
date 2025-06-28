package com.weatherreport.model;

import com.weatherreport.utils.MathUtils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de représentation de la météo heure par heure.
 * @author DevBlocks42
 */
public class ForecastDay {
    /**
     * Lieu concernant la météo à afficher
     */
    private Location location = null;
    /**
     * Liste des températures sur 24 heures
     */
    private List<Float> temperatures;
    /**
     * Liste des températures ressenties sur 24 heures
     */
    private List<Float> apparent_temperatures;
    /**
     * Liste des probabilités de pluie sur 24 heures
     */
    private List<Integer> precipitation_probability;
    /**
     * Liste des précipitations sur 24 heures
     */
    private List<Float> rain;
    /**
     * Liste des données de couverture nuageuse sur 24 heures
     */
    private List<Integer> cloud_cover;
    /**
     * Liste des vitesses du vent sur 24 heures
     */
    private List<Float> wind_speed_10m;
    /**
     * Liste des codes météos sur 24 heures
     */
    private List<Integer> weather_code;
    /**
     * Liste des heures de la journée
     */
    private List<LocalTime> dailyClock = new ArrayList<>();
    /**
     * Constructeur 
     * @param Location location
     * @param List<Float> temperatures
     * @param List<Float> apparent_temperatures
     * @param List<Integer> precipitation_probability
     * @param List<Float> rain
     * @param List<Integer> cloud_cover
     * @param List<Float> wind_speed_10m
     * @param List<Integer> weather_code 
     */
    public ForecastDay(Location location, List<Float> temperatures, List<Float> apparent_temperatures, List<Integer> precipitation_probability, List<Float> rain, List<Integer> cloud_cover, List<Float> wind_speed_10m, List<Integer> weather_code) {
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
    /**
     * Constreteur vide
     */
    public ForecastDay() {
    }
    /**
     * Retourne la liste des températures sur 24 heures
     * @return List<Float> temperatures
     */
    public List<Float> getTemperatures() {
        return this.temperatures;
    }
    /**
     * Retourne la liste des températures ressenties sur 24 heures
     * @return List<Float> apparent_temperatures
     */
    public List<Float> getApparentTemperatures() {
        return this.apparent_temperatures;
    }
    /**
     * Retourne la liste des probabilités de pluie sur 24 heures
     * @return List<Integer> precipitation_probability
     */
    public List<Integer> getPrecipitationProbs() {
        return this.precipitation_probability;
    }
    /**
     * Retourne les précipitations sur 24 heures
     * @return List<Float> rain
     */
    public List<Float> getRain() {
        return this.rain;
    }
    /**
     * Retourne la couverture nuageuse sur 24 heures
     * @return List<Integer> cloud_cover
     */
    public List<Integer> getCloudCover() {
        return this.cloud_cover;
    }
    /**
     * Retourne la liste des vitesses du vent sur 24 heures
     * @return List<Float> wind_speed_10m
     */
    public List<Float> getWindSpeed10m() {
        return this.wind_speed_10m;
    }
    /**
     * Retourne la liste des codes météos sur 24 heures
     * @return List<Integer> weather_code;
     */
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
    /**
     * Retourne la liste des heures de la journée 
     * @return 
     */
    public List<LocalTime> getDailyClock() {
        return this.dailyClock;
    }
}
