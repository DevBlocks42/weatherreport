package com.weatherreport.model;

import javafx.scene.image.Image;

/**
 * Classe représentant une icone météo 
 * @author DevBlocks42
 */
public class WeatherIcon {
    private int weather_code;
    private String description;
    private Image image;
    
    public WeatherIcon(int weather_code, String description, Image image) {
        this.weather_code = weather_code;
        this.description = description;
        this.image = image;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Image getImage() {
        return image;
    }
    
}
