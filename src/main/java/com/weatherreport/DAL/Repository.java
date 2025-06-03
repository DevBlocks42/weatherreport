package com.weatherreport.DAL;

import com.weatherreport.http.ApiClient;

public class Repository {
    protected ApiClient apiClient;
    
    public Repository() {
    }
    
    public ApiClient getApiClient() {
        return this.apiClient;
    }
}
