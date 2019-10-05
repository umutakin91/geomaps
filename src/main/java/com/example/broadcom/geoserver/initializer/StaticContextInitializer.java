package com.example.broadcom.geoserver.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import com.example.broadcom.geoserver.util.GeoServerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * initializes static beans.
 *
 * @author
 */
@Component
public class StaticContextInitializer {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        GeoServerUtil.setObjectMapper(objectMapper);
    }
}
