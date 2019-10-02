package com.example.broadcom.geoserver.service;

import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;

public interface GeoNamesService {

    SearchResult search(SearchQueryDto q);
}
