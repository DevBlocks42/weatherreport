package com.weatherreport.model;

/**
 * Classe de représentation des lieux
 * @author DevBlocks42
 */
public class Location {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timezone;
    private String feature_code;
    private String country_code;
    private String country;
    private int country_id;
    private int population;
    private String admin1, admin2, admin3, admin4;
    
    public Location(int id, String name, double latitude, double longitude, double elevation, String timezone, String feature_code, String country_code, String country, int country_id, int population, String admin1, String admin2, String admin3, String admin4) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.timezone = timezone;
        this.feature_code = feature_code;
        this.country_code = country_code;
        this.country = country;
        this.country_id = country_id;
        this.population = population;
        this.admin1 = admin1;
        this.admin2 = admin2;
        this.admin3 = admin3;
        this.admin4 = admin4;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCountry() {
        return country;
    }
    
    public double getElevation() {
        return elevation;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public String getCountryCode() {
        return country_code;
    }
    
    public String getAdmin1() {
        return admin1;
    }
    
    public String toString() {
        String string = "id : " + id + " nom : " + name + " lat : " + latitude + " lon : " + longitude + " alt : " + elevation + " timezone : " + timezone + " pays : " + country + " ville : " + admin1;
        return string;
    }
}
