package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserOuterResponce {

    @SerializedName("user")
    public User user;
    @SerializedName("status")
    public String status;
}
