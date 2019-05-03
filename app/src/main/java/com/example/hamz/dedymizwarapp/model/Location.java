package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Location {

    @SerializedName("id")
    int id;
    @SerializedName("location")
    private String imageLocationName;
    @SerializedName("description")
    private String description;

    @SerializedName("images")
    private ArrayList<ImageMap> imageMaps;

    public Location(int id, String imageLocationName, String description, ArrayList<ImageMap> imageMaps) {
        this.id = id;
        this.imageLocationName = imageLocationName;
        this.description = description;
        this.imageMaps = imageMaps;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ImageMap> getImageMaps() {
        return imageMaps;
    }

    public void setImageMaps(ArrayList<ImageMap> imageMaps) {
        this.imageMaps = imageMaps;
    }
}
