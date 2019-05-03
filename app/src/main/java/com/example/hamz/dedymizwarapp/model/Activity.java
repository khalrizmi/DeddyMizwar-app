package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class Activity {

    @SerializedName("id")
    int id;
    @SerializedName("image")
    String image;
    @SerializedName("title")
    String title;
    @SerializedName("date")
    String date;
    @SerializedName("description")
    String description;

    public Activity(int id, String image, String title, String date, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
