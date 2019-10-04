package com.example.broadcom.geoserver.util;

import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoServerUtil {

    private static ObjectMapper objectMapper;

    public static MultiValueMap<String, String> convertObjectToMultiValueMap(Object obj) {
        MultiValueMap parameters = new LinkedMultiValueMap();
        Map<String, String> maps = objectMapper.convertValue(obj, new TypeReference<Map<String, String>>() {});
        parameters.setAll(maps);

        return parameters;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        GeoServerUtil.objectMapper = objectMapper;
    }
}
