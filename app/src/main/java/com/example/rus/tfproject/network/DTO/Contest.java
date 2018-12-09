package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contest {

    @SerializedName("title")
    public String title;
    @SerializedName("rel_titles")
    public String relTitles;
    @SerializedName("time_left")
    public Integer timeLeft;
    @SerializedName("duration")
    public Integer duration;

}
