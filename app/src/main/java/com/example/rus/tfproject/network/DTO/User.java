package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("birthday")
    @Expose
    public String birthday;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("middle_name")
    @Expose
    public String middleName;
    @SerializedName("phone_mobile")
    @Expose
    public String phoneMobile;
    @SerializedName("t_shirt_size")
    @Expose
    public String tShirtSize;
    @SerializedName("is_client")
    @Expose
    public Boolean isClient;
    @SerializedName("skype_login")
    @Expose
    public Object skypeLogin;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("school")
    @Expose
    public Object school;
    @SerializedName("school_graduation")
    @Expose
    public Object schoolGraduation;
    @SerializedName("university")
    @Expose
    public String university;
    @SerializedName("faculty")
    @Expose
    public String faculty;
    @SerializedName("university_graduation")
    @Expose
    public Integer universityGraduation;
    @SerializedName("grade")
    @Expose
    public Object grade;
    @SerializedName("department")
    @Expose
    public Object department;
    @SerializedName("current_work")
    @Expose
    public Object currentWork;
    @SerializedName("resume")
    @Expose
    public String resume;
    @SerializedName("notifications")
    @Expose
    public List<Object> notifications = null;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("admin")
    @Expose
    public Boolean admin;
    @SerializedName("avatar")
    @Expose
    public Object avatar;
}
