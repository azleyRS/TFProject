package com.example.rus.tfproject.network;

import com.example.rus.tfproject.network.DTO.HomeWorks;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeworksEndpoint {
    @GET("course/android_fall2018/homeworks")
    Single<HomeWorks> getHomeWorks();
}
