package com.example.broadcom.geoserver.ws.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

/**
 * The interface <i>RestClient</i> provides methods like <i>get</i>
 * and others for sending <i>REST</i> calls to third parties.
 *
 * @author Umut AKIN
 */
public interface RestClient {

    <T> ResponseEntity<? extends T> get(String url, HttpHeaders headers, Class<? extends T> resultType) throws RestClientException;

    <T> ResponseEntity<? extends T> get(String url, Class<? extends T> resultType) throws RestClientException;

}
