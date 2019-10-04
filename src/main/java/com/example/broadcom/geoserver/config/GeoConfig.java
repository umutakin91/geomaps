package com.example.broadcom.geoserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.example.broadcom.geoserver.ws.rest.RestClient;
import com.example.broadcom.geoserver.ws.rest.RestDefaultClientImpl;

@Configuration
@EnableCaching
public class GeoConfig {

    @Autowired
    private ResponseErrorHandler responseErrorHandler;

    @Bean("geonamesRestClient")
    @Primary
    public RestClient restClient() {
        return new RestDefaultClientImpl(getRestTemplate());
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return createRestTemplate();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("geonames");
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }
}