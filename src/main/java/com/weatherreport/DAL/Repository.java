package com.weatherreport.DAL;

import com.weatherreport.http.ApiClient;

/**
 * Singleton : classe de base pour les dépôts 
 * @author DevBlocks42 
 */
public class Repository {
    protected static Repository instance = null;
    private ApiClient apiClient;
    /**
     * Constructeur
     */
    private Repository() {
        apiClient = new ApiClient();
    }
    /**
     * Retourne l'instance du singleton (en créer une nouvelle si aucune n'existe)
     * @return repository
     */
    public static Repository getInstance() {
        if(instance == null) {
            return new Repository();
        }
        return instance;
    }
    /**
     * Retourne l'instance du client API
     * @return apiClient
     */
    public ApiClient getApiClient() {
        return this.apiClient;
    }
}
