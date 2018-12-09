package com.example.rus.tfproject.network;

import com.example.rus.tfproject.network.DTO.HomeWorks;
import com.example.rus.tfproject.network.DTO.TestQuestion;
import com.example.rus.tfproject.network.DTO.TestStatus;
import com.google.gson.JsonElement;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HomeworksEndpoint {
    @GET("course/android_fall2018/homeworks")
    Single<HomeWorks> getHomeWorks();

    @GET("contest/{contest_url}/status")
    Single<TestStatus> getTestStatus(@Path("contest_url") String contestUrl);

    @POST("contest/{contest_url}/start_contest")
    Single<retrofit2.Response<Object>> startTest(@Path("contest_url") String contestUrl, @Body JsonElement element);

    @GET("contest/{contest_url}/problems")
    Single<List<TestQuestion>> getQuestionsList(@Path("contest_url") String contestUrl);

    @GET("contest/{contest_url}/problem/{id}")
    Single<TestQuestion> getQuestion(@Path("contest_url") String contestUrl,
                                       @Path("id") String id);

    @POST("contest/{contest_url}/problem/{id}")
    @FormUrlEncoded
    Single<TestQuestion> answerQuestion(
            @Path("contest_url") String contestUrl,
            @Path("id") String id,
            @Field("answer") String answer,
            @Field("language") Integer language);


}
