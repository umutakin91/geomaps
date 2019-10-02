package com.example.broadcom.geoserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;

@Service
public class GeoNamesServiceImpl implements GeoNamesService {

    @Autowired
    @Qualifier(value = "geonamesRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public SearchResult search(SearchQueryDto searchQueryDto) {

        final String uri = "http://api.geonames.org/searchJSON?formatted=true&q="+ searchQueryDto.getQuery() +"&maxRows=2&lang=es&username=geosetter&style=MEDIUM";

        SearchResult result = restTemplate.getForObject(uri, SearchResult.class);

        System.out.println(result);
        return result;
    }
}
