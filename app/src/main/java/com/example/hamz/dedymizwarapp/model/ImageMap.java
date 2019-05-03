package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class ImageMap {

    @SerializedName("image")
    private String image;

    public ImageMap(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
