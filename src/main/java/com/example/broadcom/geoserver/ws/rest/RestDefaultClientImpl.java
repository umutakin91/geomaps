package com.example.broadcom.geoserver.ws.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestDefaultClientImpl implements RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestDefaultClientImpl.class);

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
            logSocketError(url, e);
            throw e;
        }
    }


    private void logSocketError(String url, RestClientException e) {
        LOGGER.error("Error when connectiong to the server, URL = [{}]", url, e);
    }

}
