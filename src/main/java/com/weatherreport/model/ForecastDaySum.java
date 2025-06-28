package com.weatherreport.model;

import java.time.LocalDateTime;

/**
 * Classe de représentation de la météo globale du jour
 * @author debvblocks42
 */
public class ForecastDaySum {
    /**
     * Lieu concernant la synthèse météo hebdomadaire
     */
    private Location location = null;
    /**
     * Code météo
     */
    private int weather_code;
    /**
     * Heure du lever du soleil
     */
    private LocalDateTime sunrise;
    /**
     * Heure du coucher du soleil
     */
    private LocalDateTime sunset;
    /**
     * Température maximale
     */
    private Float maxTemp;
    /**
     * Température minimmale
     */
    private Float minTemp;
    /**
     * Constructeur de l'objet
     * @param Location location
     * @param int weather_code
     * @param LocalDateTime sunrise
     * @param LocalDateTime sunset
     * @param Float maxTemp
     * @param Float minTemp 
     */
    public ForecastDaySum(Location location, int weather_code, LocalDateTime sunrise, LocalDateTime sunset, Float maxTemp, Float minTemp) {
        this.location = location;
        this.weather_code = weather_code;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
    /**
     * Retourne le code météo
     * @return int weather_code
     */
    public int getWeatherCode() {
        return weather_code;
    }
    /**
     * Retourne l'heure du lever du soleil
     * @return LocalDateTime sunrise
     */
    public LocalDateTime getSunrise() {
        return sunrise;
    }
    /**
     * Retourne l'heure du coucher du soleil
     * @return LocalDateTime sunset
     */
    public LocalDateTime getSunset() {
        return sunset;
    }
    /**
     * Retourne la température maximale
     * @return Float maxTemp;
     */
    public Float getMaxTemp() {
        return maxTemp;
    }
    /**
     * Retourne la température minimale
     * @return Float minTemp
     */
    public Float getMinTemp() {
        return minTemp;
    }
    /**
     * Retourne le lieu associé à la synthèse hebdomadaire
     * @return Location location
     */
    public Location getLocation() {
        return location;
    }
}
