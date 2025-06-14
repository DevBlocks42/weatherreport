package com.weatherreport.DAL;

import com.weatherreport.http.ApiClient;

/**
 * Singleton : classe de base pour les dépôts 
 * @author DevBlocks42 
 */
public class Repository {
    protected static Repository instance = null;
    private ApiClient apiClient;
    
    private Repository() {
        apiClient = new ApiClient();
    }
    
    public static Repository getInstance() {
        if(instance == null) {
            return new Repository();
        }
        return instance;
    }
    
    public ApiClient getApiClient() {
        return this.apiClient;
    }
}
