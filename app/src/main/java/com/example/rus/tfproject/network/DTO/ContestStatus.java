package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class ContestStatus {
    @SerializedName("time_left")
    public String timeLeft;
    @SerializedName("status")
    public String status;
}
