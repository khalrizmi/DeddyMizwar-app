package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Location;
import com.example.hamz.dedymizwarapp.model.Map;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationList {

    @SerializedName("detail")
    private Location location;

    public LocationList(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
