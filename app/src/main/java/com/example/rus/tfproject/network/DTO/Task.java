package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    public Integer id;
    @SerializedName("task")
    public DetailedTask task;
    @SerializedName("status")
    public String status;
    @SerializedName("mark")
    public String mark;
}
