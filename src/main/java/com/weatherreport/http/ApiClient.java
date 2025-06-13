package com.weatherreport.http;

import com.weatherreport.utils.PropertiesReader;
import java.io.IOException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 * Classe responsable de l'envoie et de la récéption des données vers les API
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class ApiClient {
    private final String geocodingApiURL = PropertiesReader.getProperty("GEOCODING_API_URL");
    private final String openmeteoApiURL = PropertiesReader.getProperty("OPENMETEO_API_URL");
    private final String openweatherApiURL = PropertiesReader.getProperty("OPENWEATHER_API_URL");
    private HttpClient httpClient;
    
    public ApiClient() {
        httpClient = HttpClients.createDefault();
    }
    
    
    private HttpGet configureGetRequest(String apiURL, String URI) {
        HttpGet request = new HttpGet(apiURL + URI);
        request.addHeader("accept", "*/*");
        return request;
    }
    
    /**
     * Envoie une requête HTTP GET à l'adresse spécifiée
     * @param apiURL URL de l'API
     * @param URI Ressource/route à intérroger
     * @return une HttpEntityResponse correspondant à la réponse reçue
     * @throws IOException 
     */
    public HttpEntityResponse sendGetRequest(String apiURL, String URI) throws IOException {
        HttpGet request = configureGetRequest(apiURL, URI);
        HttpEntityResponse entityResponse = httpClient.execute(request, response -> {
            return new HttpEntityResponse(response.getCode(), EntityUtils.toString(response.getEntity()));
        });
        return entityResponse;
    }
    
    public HttpBytesEntityResponse sendGetRequestToBytes(String apiURL, String URI) throws IOException {
        HttpGet request = configureGetRequest(apiURL, URI);
        HttpBytesEntityResponse entityResponse = httpClient.execute(request, response -> {
            return new HttpBytesEntityResponse(response.getCode(), EntityUtils.toByteArray(response.getEntity()));
        });
        return entityResponse;
    }
    
    public final String getGeocodingApiURL() {
        return geocodingApiURL;
    }
    
    public final String getOpenmeteoApiURL() {
        return openmeteoApiURL;
    }
    
    public final String getOpenweatherApiURL() {
        return openweatherApiURL;
    }
    
}
