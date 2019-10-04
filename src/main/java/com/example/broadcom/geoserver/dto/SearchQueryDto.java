package com.example.broadcom.geoserver.dto;

import java.io.Serializable;

public class SearchQueryDto implements Serializable {

    String q;
    String lang;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "SearchQueryDto{" +
                "q='" + q + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
