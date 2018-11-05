package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Homework {
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("tasks")
    public List<Task> tasks;
}
