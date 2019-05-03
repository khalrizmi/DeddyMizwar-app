package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Map;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapList {
    @SerializedName("data")
    private List<Map> mData;

    public MapList(List<Map> mData) {
        this.mData = mData;
    }

    public MapList() {
    }

    public List<Map> getmData() {
        return mData;
    }

    public void setmData(List<Map> mData) {
        this.mData = mData;
    }
}
