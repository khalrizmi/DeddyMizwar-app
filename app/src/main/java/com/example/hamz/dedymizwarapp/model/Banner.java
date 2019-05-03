package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("image")
    private String image;

    public Banner(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
