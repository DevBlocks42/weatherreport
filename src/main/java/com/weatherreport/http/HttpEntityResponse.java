package com.weatherreport.http;

/**
 * Classe de représentation des données du corps des réponses reçues des API
 * @author DevBlocks42 <devblocks42 at keemail.me>
 */
public class HttpEntityResponse {
    private int status;
    private String content;
    
    public HttpEntityResponse(int status, String content) {
        this.status = status;
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }   
}
