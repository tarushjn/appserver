package com.jwt.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by saurabhnagpal on 17/07/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUrl {


    private String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
         str.append(" First Name:- " + getImageUrl());
        return str.toString();
    }
}
