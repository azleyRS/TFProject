package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class DetailedTask {
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("task_type")
    public String taskType;
    @SerializedName("max_score")
    public String maxScore;
    @SerializedName("deadline_date")
    public String deadlineDate;
    @SerializedName("contest_info")
    public Object contestInfo;
    @SerializedName("short_name")
    public String shortName;
}
