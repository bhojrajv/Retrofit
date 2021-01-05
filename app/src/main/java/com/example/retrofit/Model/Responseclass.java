package com.example.retrofit.Model;

import com.google.gson.annotations.SerializedName;

public class Responseclass {
    @SerializedName("error")
    private boolean err;
    @SerializedName("message")
    private String msg;

    public Responseclass(boolean err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    public boolean isErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }
}
