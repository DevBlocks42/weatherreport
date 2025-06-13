/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weatherreport.http;

/**
 *
 * @author DevBlocks42 <devblocks42 at keemail.me>
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

