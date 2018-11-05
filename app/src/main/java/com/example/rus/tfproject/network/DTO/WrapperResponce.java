package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class WrapperResponce {
    @SerializedName("ErrorResponse")
    public ErrorResponse errorResponse;
    @SerializedName("UserResponse")
    public User user;
}
