package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class LastSubmission {

    @SerializedName("id")
    public Integer id;
    @SerializedName("status")
    public String status;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("programming_language")
    public String programmingLanguage;
    @SerializedName("file")
    public String file;
    @SerializedName("report")
    public String report;
    @SerializedName("problem_name")
    public String problemName;
    @SerializedName("problem_id")
    public Integer problemId;

}
