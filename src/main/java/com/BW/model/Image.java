package com.BW.model;

import java.io.Serializable;

public class Image implements Serializable {

    private int id;
    private String imageurl;

    public Image(int id, String imageurl) {
        this.id = id;
        this.imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}