package com.weatherreport.DAL;

import com.weatherreport.http.ApiClient;
import com.weatherreport.http.HttpEntityResponse;
import com.weatherreport.model.Location;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe responsable de l'accès aux données de lieux via l'API geocoding
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class LocationRepository {
    private ApiClient apiClient;
    
    public LocationRepository(Repository repository) {
        apiClient = repository.getApiClient();
    }
    
    /**
     * Retourne la liste des lieux proches d'un mot clef
     * @param searchTerm
     * @return une List de Location 
     */
    public List<Location> getLocationsLike(String searchTerm) {
        List<Location> locations = new ArrayList<>();
        try {
            HttpEntityResponse response = apiClient.sendGetRequest(apiClient.getGeocodingApiURL(), "/search?name=" + URLEncoder.encode(searchTerm, StandardCharsets.UTF_8) + "&count=20");
            String textResponse = response.getContent();
            if(textResponse.indexOf("[") != -1) {
                JSONArray jsonArray = new JSONArray(textResponse.substring(textResponse.indexOf("[")));
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject currentLocation = jsonArray.getJSONObject(i);
                    int id = currentLocation.optInt("id", -1);
                    String name = currentLocation.optString("name", "");
                    double latitude = currentLocation.optDouble("latitude", -1.0);
                    double longitude = currentLocation.optDouble("longitude", -1.0);
                    double elevation = currentLocation.optDouble("elevation", -999.0);
                    String timezone = currentLocation.optString("timezone", "");
                    String feature_code = currentLocation.optString("feature_code", "");
                    String country_code = currentLocation.optString("country_code", "");
                    String country = currentLocation.optString("country", "");
                    int country_id = currentLocation.optInt("country_id", -1);
                    int population = currentLocation.optInt("population", -1);
                    String admin1 = currentLocation.optString("admin1", "");
                    String admin2 = currentLocation.optString("admin2", "");
                    String admin3 = currentLocation.optString("admin3", "");
                    String admin4 = currentLocation.optString("admin4", "");
                    Location station = new Location(id, name, latitude, longitude, elevation, timezone, feature_code, country_code, country, country_id, population, admin1, admin2, admin3, admin4);
                    locations.add(station);
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return locations;
    }
}
