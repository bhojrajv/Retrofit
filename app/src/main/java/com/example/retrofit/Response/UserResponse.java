package com.example.retrofit.Response;

import com.example.retrofit.Model.User;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("error")
   private boolean error;
   private String message;
    private User user;

    public UserResponse(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
