package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class ContestInfo {
    @SerializedName("contest_status")
    public ContestStatus contestStatus;
    @SerializedName("contest_url")
    public String contestUrl;
}
