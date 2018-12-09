package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestStatus {
    @SerializedName("contest")
    @Expose
    public Contest contest;
    @SerializedName("problems")
    @Expose
    public List<Object> problems = null;
}
