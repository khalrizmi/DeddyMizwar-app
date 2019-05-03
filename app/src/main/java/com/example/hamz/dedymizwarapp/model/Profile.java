package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("image")
    String image;
    @SerializedName("description")
    String description;

    public Profile(){}

    public Profile(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
