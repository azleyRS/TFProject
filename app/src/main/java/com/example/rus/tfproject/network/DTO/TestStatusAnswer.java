package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestStatusAnswer {

    @SerializedName("contest")
    public Contest contest;
    @SerializedName("problems")
    public List<Object> problems = null;
}
