package com.weatherreport.http;

/**
 * Classe de représentation des données brutes du corps des réponses reçues des API (utile pour télécharger des binaires)
 * @author DevBlocks42 
 */
public class HttpBytesEntityResponse {
    private int status;
    private byte[] content;
    
    public HttpBytesEntityResponse(int status, byte[] content) {
        this.status = status;
        this.content = content;
    }
    
    public byte[] getContent() {
        return this.content;
    }   
    
}

