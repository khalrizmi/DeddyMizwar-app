package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Video;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoList {

    @SerializedName("data")
    private ArrayList<Video> videos;

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
}
