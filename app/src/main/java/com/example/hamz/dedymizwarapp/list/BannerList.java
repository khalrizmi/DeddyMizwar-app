package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Banner;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerList {

    @SerializedName("data")
    private ArrayList<Banner> banners;

    public BannerList(ArrayList<Banner> banners) {
        this.banners = banners;
    }

    public ArrayList<Banner> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<Banner> banners) {
        this.banners = banners;
    }
}
