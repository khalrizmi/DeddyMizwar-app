package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.City;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityList {

    @SerializedName("data")
    ArrayList<City> cities;

    public CityList(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
