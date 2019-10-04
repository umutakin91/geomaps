package com.example.broadcom.geoserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.ws.rest.RestClient;

@Service
public class GeoNamesServiceImpl implements GeoNamesService {

    @Value("${geonames.ws.url.search}")
    private String geoNamesServiceUrl;

    @Autowired
    @Qualifier(value = "geonamesRestClient")
    private RestClient restClient;

    @Override
    public SearchResult search(SearchQueryDto searchQueryDto) {

        String uri= UriComponentsBuilder.fromUriString(geoNamesServiceUrl)
                .queryParam("q", searchQueryDto.getQ())
                .buildAndExpand()
                .toUriString();

        ResponseEntity<? extends SearchResult> result = restClient.get(uri, SearchResult.class);
        SearchResult searchResult = result.getBody();

        return searchResult;
    }
}
