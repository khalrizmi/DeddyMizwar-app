package com.example.hamz.dedymizwarapp.list;

import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("id")
    int id;
    @SerializedName("api_token")
    String token;
    @SerializedName("name")
    String name;
    @SerializedName("message")
    String message;
    @SerializedName("email")
    String email;
    @SerializedName("messageError")
    String messageError;

    public UserList(int id, String token, String name, String message, String email, String messageError) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.message = message;
        this.email = email;
        this.messageError = messageError;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
