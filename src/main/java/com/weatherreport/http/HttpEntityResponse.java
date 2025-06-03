
package com.weatherreport.http;

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
