package com.weatherreport.http;

/**
 * Classe de représentation des données brutes du corps des réponses reçues des API (utile pour télécharger des binaires)
 * @author DevBlocks42 
 */
public class HttpBytesEntityResponse {
    /**
     * Code de réponse HTTP reçu
     */
    private int status;
    /**
     * Contenu de la réponse sous forme de tableau d'octets
     */
    private byte[] content;
    /**
     * Constructeur
     * @param status
     * @param content 
     */
    public HttpBytesEntityResponse(int status, byte[] content) {
        this.status = status;
        this.content = content;
    }
    /**
     * Retourne la réponse de la requête
     * @return byte[] content
     */
    public byte[] getContent() {
        return this.content;
    }    
}

