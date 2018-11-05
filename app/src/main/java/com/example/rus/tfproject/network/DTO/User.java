package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("birthday")
    public String birthday;
    @SerializedName("email")
    public String email;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("middle_name")
    public String middleName;
    @SerializedName("phone_mobile")
    public String phoneMobile;
    @SerializedName("t_shirt_size")
    public String tShirtSize;
    @SerializedName("is_client")
    public Boolean isClient;
    @SerializedName("skype_login")
    public Object skypeLogin;
    @SerializedName("description")
    public String description;
    @SerializedName("region")
    public String region;
    @SerializedName("school")
    public Object school;
    @SerializedName("school_graduation")
    public Object schoolGraduation;
    @SerializedName("university")
    public String university;
    @SerializedName("faculty")
    public String faculty;
    @SerializedName("university_graduation")
    public Integer universityGraduation;
    @SerializedName("grade")
    public Object grade;
    @SerializedName("department")
    public Object department;
    @SerializedName("current_work")
    public Object currentWork;
    @SerializedName("resume")
    public String resume;
    @SerializedName("notifications")
    public List<Object> notifications = null;
    @SerializedName("id")
    public Integer id;
    @SerializedName("admin")
    public Boolean admin;
    @SerializedName("avatar")
    public Object avatar;
}
