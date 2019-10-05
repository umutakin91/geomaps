package com.example.broadcom.geoserver.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class SearchQueryDto implements Serializable {

    @NotBlank(message = "Search Parameter Should Not Be Blank")
    private String q;
    private String lang;
    private Integer startRow = 0;

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

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    @Override
    public String toString() {
        return "SearchQueryDto{" +
                "q='" + q + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
