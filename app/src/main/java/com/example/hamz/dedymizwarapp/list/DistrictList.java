package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.District;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DistrictList {

    @SerializedName("data")
    ArrayList<District> districts;

    public DistrictList(ArrayList<District> districts) {
        this.districts = districts;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
}
