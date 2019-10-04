package com.example.broadcom.geoserver.ws.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestDefaultClientImpl implements RestClient {


    private final RestTemplate restTemplate;


    public RestDefaultClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> ResponseEntity<? extends T> get(String url, @Nullable HttpHeaders headers, Class<? extends T> resultType) throws RestClientException {
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), resultType);
    }

    @Override
    public <T> ResponseEntity<? extends T> get(String url, Class<? extends T> resultType) throws RestClientException {
        try {
            return this.get(url, HttpHeaders.EMPTY, resultType);
        } catch (RestClientException e) {
            // TODO log error.
            throw e;
        }
    }

}
