package com.weatherreport.http;


/**
 * Classe de représentation des données du corps des réponses reçues des API
 * @author DevBlocks42 
 */
public class HttpEntityResponse {
    /**
     * Code de réponse http reçu
     */
    private int status;
    /**
     * Contenu de la réponse
     */
    private String content;
    /**
     * Constructeur
     * @param status
     * @param content 
     */
    public HttpEntityResponse(int status, String content) {
        this.status = status;
        this.content = content;
    }
    /**
     * Retourne le contenu de la réponse
     * @return String content
     */
    public String getContent() {
        return this.content;
    }   
    
}
