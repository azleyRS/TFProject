package com.example.rus.tfproject.network;

import com.example.rus.tfproject.network.DTO.Login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthEndpoint {
    @POST("signin")
    @FormUrlEncoded
    Observable<Login> authenticate(@Field("email") String email,
                                   @Field("password") String password);
}
