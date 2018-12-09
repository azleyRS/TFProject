package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Problem {
    @SerializedName("id")
    public Integer id;
    @SerializedName("cms_page")
    public CmsPage cmsPage;
    @SerializedName("verbose_title")
    public String verboseTitle;
    @SerializedName("problem_type")
    public String problemType;
    @SerializedName("answer_choices")
    public List<String> answerChoices;
    @SerializedName("attempts_limit_type")
    public String attemptsLimitType;
    @SerializedName("ML")
    public String mL;
    @SerializedName("TL")
    public String tL;
    @SerializedName("problem_page_data")
    public Object problemPageData;
    @SerializedName("allowed_extensions")
    public String allowedExtensions;
    @SerializedName("max_submission_size")
    public Float maxSubmissionSize;
    @SerializedName("allowed_languages")
    public List<Object> allowedLanguages;
}
