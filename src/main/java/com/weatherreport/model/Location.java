package com.weatherreport.model;

/**
 * Classe de représentation des lieux
 * @author DevBlocks42
 */
public class Location {
    private int id;
    /**
     * Nom du lieu
     */
    private String name;
    /**
     * Latitude du lieux
     */
    private double latitude;
    /**
     * Longitude du lieu
     */
    private double longitude;
    /**
     * Altitude du lieu
     */
    private double elevation;
    /**
     * TimeZone du lieu
     */
    private String timezone;
    private String feature_code;
    private String country_code;
    private String country;
    private int country_id;
    private int population;
    /**
     * Zones administratives
     */
    private String admin1, admin2, admin3, admin4;
    /**
     * Initialise un nouvel objet Location
     * @param int id
     * @param String name
     * @param double latitude
     * @param double longitude
     * @param double elevation
     * @param String timezone
     * @param String feature_code
     * @param String country_code
     * @param String country
     * @param int country_id
     * @param int population
     * @param String admin1
     * @param String admin2
     * @param String admin3
     * @param String admin4 
     */
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
    /**
     * Retourne le nom du lieu
     * @return String name
     */
    public String getName() {
        return name;
    }
    /**
     * Retourne le nom du pays du lieu
     * @return String country
     */
    public String getCountry() {
        return country;
    }
    /**
     * Retourne l'altitude du lieu
     * @return double elevation
     */
    public double getElevation() {
        return elevation;
    }
    /**
     * Retourne la latitude du lieu
     * @return double latitude
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * Retourne la longitude du lieu
     * @return doube longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    public String getCountryCode() {
        return country_code;
    }
    /**
     * Retourne la zone administrative du lieu (Administration, Région, District etc..)
     * @return String admin1
     */
    public String getAdmin1() {
        return admin1;
    }
    
    public String toString() {
        String string = "id : " + id + " nom : " + name + " lat : " + latitude + " lon : " + longitude + " alt : " + elevation + " timezone : " + timezone + " pays : " + country + " ville : " + admin1;
        return string;
    }
}
