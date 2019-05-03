package com.example.hamz.dedymizwarapp.model;

public class Video {

    int id;
    String video;
    String description;

    public Video(int id, String video, String description) {
        this.id = id;
        this.video = video;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
