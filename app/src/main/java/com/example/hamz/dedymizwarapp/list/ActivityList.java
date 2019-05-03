package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Activity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ActivityList {

    @SerializedName("data")
    private ArrayList<Activity> activities;

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    @SerializedName("detail")
    private  Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
