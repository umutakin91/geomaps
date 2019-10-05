package com.example.broadcom.geoserver.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Util class for general purpose, converts objects.
 *
 * @author Umut AKIN
 */
public class GeoServerUtil {

    private static ObjectMapper objectMapper;

    public static MultiValueMap<String, String> convertObjectToMultiValueMap(Object obj) {
        MultiValueMap parameters = new LinkedMultiValueMap();
        Map<String, String> maps = objectMapper.convertValue(obj,
                new TypeReference<Map<String, String>>() {});
        parameters.setAll(maps);

        return parameters;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        GeoServerUtil.objectMapper = objectMapper;
    }
}
