package com.weatherreport.model;

import javafx.scene.image.Image;

/**
 * Classe représentant une icone météo 
 * @author DevBlocks42
 */
public class WeatherIcon {
    /**
     * Code météo de l'icone
     */
    private int weather_code;
    /**
     * Description textuelle de l'icône météo
     */
    private String description;
    /**
     * Image correspondant à l'icône météo
     */
    private Image image;
    
    /**
     * Initialise un nouvel objet WeatherIcon
     * @param weather_code
     * @param description
     * @param image 
     */
    public WeatherIcon(int weather_code, String description, Image image) {
        this.weather_code = weather_code;
        this.description = description;
        this.image = image;
    }
    /**
     * Retourne la description de l'icône météo
     * @return String description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Retourne l'image du symbole météo
     * @return Image image
     */
    public Image getImage() {
        return image;
    }
    
}
