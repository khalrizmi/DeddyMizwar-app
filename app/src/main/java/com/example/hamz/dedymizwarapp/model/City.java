package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
