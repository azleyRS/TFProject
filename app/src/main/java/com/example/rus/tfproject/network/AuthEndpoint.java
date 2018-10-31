package com.example.rus.tfproject.network;

import com.example.rus.tfproject.network.DTO.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthEndpoint {
    @POST("signin")
    @FormUrlEncoded
    Observable<User> authenticate(@Field("email") String email,
                                  @Field("password") String password);
}
