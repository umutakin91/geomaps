package com.example.broadcom.geoserver.dto;

import java.io.Serializable;

public class SearchQueryDto implements Serializable {

    String q;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public String toString() {
        return "SearchQueryDto{" +
                "q='" + q + '\'' +
                '}';
    }
}
