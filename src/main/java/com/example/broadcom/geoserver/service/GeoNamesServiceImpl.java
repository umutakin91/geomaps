package com.example.broadcom.geoserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.ws.rest.RestClient;

/**
 * Service implementation for geonames rest api.
 *
 * @author Umut AKIN
 */
@Service
public class GeoNamesServiceImpl implements GeoNamesService {

    @Value("${geonames.ws.url.search}")
    private String geoNamesServiceUrl;

    @Value("${geonames.ws.username}")
    private String username;

    @Value("${geonames.ws.style}")
    private String style;

    @Value("${geonames.ws.maxRows}")
    private Integer maxRows;

    @Value("${geonames.ws.formatted}")
    private Boolean formatted;

    @Value("#{'${geonames.ws.languages}'.split(',')}")
    private List<String> languageList;

    @Autowired
    @Qualifier(value = "geonamesRestClient")
    private RestClient restClient;

    @Cacheable("geonames")
    @Override
    public SearchResult search(SearchQueryDto searchQueryDto) {

        String uri = prepareGeoNamesUrl(searchQueryDto);

        ResponseEntity<? extends SearchResult> result = restClient.get(uri, SearchResult.class);
        SearchResult searchResult = result.getBody();

        setPrevAndNextStartRow(searchQueryDto, searchResult);

        return searchResult;
    }

    @Override
    public List<String> getLanguages() {
        return languageList;
    }

    private void setPrevAndNextStartRow(SearchQueryDto searchQueryDto, SearchResult searchResult) {
        //searchResult.setPrevStartRow();
        int totalResultCount = searchResult.getTotalResultsCount();
        int startRow = searchQueryDto.getStartRow();
        searchResult.setNextStartRow((totalResultCount > startRow + maxRows)
                ? (startRow + maxRows)
                : null);

        searchResult.setPrevStartRow((startRow - maxRows < 0)
                ? null
                : (startRow - maxRows));
    }

    private String prepareGeoNamesUrl(SearchQueryDto searchQueryDto) {
        return UriComponentsBuilder.fromUriString(geoNamesServiceUrl)
                .queryParam("q", searchQueryDto.getQ())
                .queryParam("lang", searchQueryDto.getLang())
                .queryParam("username", username)
                .queryParam("style", style)
                .queryParam("maxRows", maxRows)
                .queryParam("formatted", formatted)
                .queryParam("startRow", searchQueryDto.getStartRow())
                .buildAndExpand()
                .toUriString();

    }
}
