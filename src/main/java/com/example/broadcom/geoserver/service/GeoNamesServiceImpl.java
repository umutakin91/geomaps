package com.example.broadcom.geoserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.ws.rest.RestClient;

@Service
public class GeoNamesServiceImpl implements GeoNamesService {

    @Autowired
    @Qualifier(value = "geonamesRestClient")
    private RestClient restClient;

    @Override
    public SearchResult search(SearchQueryDto searchQueryDto) {

        final String uri = "http://api.geonames.org/searchJSON?formatted=true&q="+ searchQueryDto.getQ() +"&maxRows=2&lang=es&username=geosetter&style=MEDIUM";

        ResponseEntity<? extends SearchResult> result = restClient.get(uri, SearchResult.class);
        SearchResult searchResult = result.getBody();

        return searchResult;
    }
}
