package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;
}
