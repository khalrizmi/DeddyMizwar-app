package com.example.hamz.dedymizwarapp.list;

import com.google.gson.annotations.SerializedName;

public class AskList {

    @SerializedName("message")
    String message;

    public AskList(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
