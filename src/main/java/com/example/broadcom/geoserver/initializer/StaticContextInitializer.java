package com.example.broadcom.geoserver.initializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.broadcom.geoserver.util.GeoServerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StaticContextInitializer {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        GeoServerUtil.setObjectMapper(objectMapper);
    }
}