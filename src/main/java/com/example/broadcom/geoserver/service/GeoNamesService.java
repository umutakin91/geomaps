package com.example.broadcom.geoserver.service;

import java.util.List;
import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;

/**
 * Interface to call the geonames rest api and returns results.
 *
 * @author
 */
public interface GeoNamesService {

    /**
     * Searches the geonames rest api with given parameters.
     *
     * @param q
     * @return SearchResult search results with additional previous and next start rows.
     */
    SearchResult search(SearchQueryDto q);

    /**
     * gets the available languages for searching.
     *
     * @return list of language abbreviations.
     */
    List<String> getLanguages();
}
