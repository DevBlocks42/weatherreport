package com.weatherreport.http;

import com.weatherreport.utils.PropertiesReader;
import java.io.IOException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class ApiClient {
    private final String geocodingApiURL = PropertiesReader.getProperty("GEOCODING_API_URL");
    private final String openmeteoApiURL = PropertiesReader.getProperty("OPENMETEO_API_URL");
    private BasicCredentialsProvider credentialsProvider;
    private HttpClient httpClient;
    
    public ApiClient() {
        httpClient = HttpClients.createDefault();
    }
    
    private HttpGet configureGetRequest(String apiURL, String URI) {
        HttpGet request = new HttpGet(apiURL + URI);
        request.addHeader("accept", "*/*");
        //request.addHeader("apikey", apiToken);
        return request;
    }
    
    public HttpEntityResponse sendGetRequest(String apiURL, String URI) throws IOException {
        HttpGet request = configureGetRequest(apiURL, URI);
        HttpEntityResponse entityResponse = httpClient.execute(request, response -> {
            return new HttpEntityResponse(response.getCode(), EntityUtils.toString(response.getEntity()));
        });
        return entityResponse;
    }
    
    public final String getGeocodingApiURL() {
        return geocodingApiURL;
    }
    
    public final String getOpenmeteoApiURL() {
        return openmeteoApiURL;
    }
    
}
