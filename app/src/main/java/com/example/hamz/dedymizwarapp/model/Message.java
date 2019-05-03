package com.example.hamz.dedymizwarapp.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    int id;

    @SerializedName("from")
    int from;
    @SerializedName("to")
    int to;
    @SerializedName("message")
    String message;


    public Message(int id, int from, int to, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
