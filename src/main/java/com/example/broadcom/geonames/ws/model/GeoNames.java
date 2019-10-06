package com.example.broadcom.geonames.ws.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * model object to be used for geonames rest api.
 *
 * @author Umut AKIN
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoNames implements Serializable {

    private String lng;

    private String lat;

    private String name;

    private String countryName;

    private String fcodeName;


    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFcodeName() {
        return fcodeName;
    }

    public void setFcodeName(String fcodeName) {
        this.fcodeName = fcodeName;
    }
}
