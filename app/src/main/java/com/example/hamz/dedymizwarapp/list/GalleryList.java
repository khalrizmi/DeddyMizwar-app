package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Gallery;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryList {

    @SerializedName("data")
    private ArrayList<Gallery> galleries;

    public ArrayList<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(ArrayList<Gallery> galleries) {
        this.galleries = galleries;
    }
}
