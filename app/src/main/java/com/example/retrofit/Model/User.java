package com.example.retrofit.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
  private   String email,name,school;

    public User(int id, String email, String name, String school) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }
}
