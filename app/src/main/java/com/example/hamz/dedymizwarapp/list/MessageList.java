package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Message;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MessageList {

    @SerializedName("id")
    int error;

    @SerializedName("data")
    ArrayList<Message> messages;

    public MessageList(int error, ArrayList<Message> messages) {
        this.error = error;
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public MessageList(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
