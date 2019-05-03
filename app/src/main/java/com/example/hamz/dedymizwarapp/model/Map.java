package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class Map {

    @SerializedName("id")
    int id;
    @SerializedName("location")
    private String imageLocationName;
    @SerializedName("latitude")
    private String latutide;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("district_id")
    private int district_id;
    @SerializedName("description")
    private String description;


    public Map(int id, String imageLocationName, String latutide, String longitude, int district_id, String description) {
        this.id = id;
        this.imageLocationName = imageLocationName;
        this.latutide = latutide;
        this.longitude = longitude;
        this.district_id = district_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLocationName() {
        return imageLocationName;
    }

    public void setImageLocationName(String imageLocationName) {
        this.imageLocationName = imageLocationName;
    }

    public String getLatutide() {
        return latutide;
    }

    public void setLatutide(String latutide) {
        this.latutide = latutide;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
