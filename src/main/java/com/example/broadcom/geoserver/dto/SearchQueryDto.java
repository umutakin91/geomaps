package com.example.broadcom.geoserver.dto;

public class SearchQueryDto {

    String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "SearchQueryDto{" +
                "query='" + query + '\'' +
                '}';
    }
}
