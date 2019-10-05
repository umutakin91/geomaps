package com.example.broadcom.geonames.ws.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult implements Serializable {

    private Integer totalResultsCount;
    private List<GeoNames> geonames;
    private Integer prevStartRow;
    private Integer nextStartRow;

    public Integer getTotalResultsCount() {
        return totalResultsCount;
    }

    public void setTotalResultsCount(Integer totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    public List<GeoNames> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<GeoNames> geonames) {
        this.geonames = geonames;
    }

    public Integer getPrevStartRow() {
        return prevStartRow;
    }

    public void setPrevStartRow(Integer prevStartRow) {
        this.prevStartRow = prevStartRow;
    }

    public Integer getNextStartRow() {
        return nextStartRow;
    }

    public void setNextStartRow(Integer nextStartRow) {
        this.nextStartRow = nextStartRow;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "totalResultsCount=" + totalResultsCount +
                ", geonames=" + geonames +
                ", prevStartRow=" + prevStartRow +
                ", nextStartRow=" + nextStartRow +
                '}';
    }
}
