package com.example.rus.tfproject.network;

import com.example.rus.tfproject.network.DTO.User;
import com.example.rus.tfproject.network.DTO.WrapperResponce;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthEndpoint {

    @POST("signin")
    @FormUrlEncoded
    Single<Response<User>> authenticateSingle(@Field("email") String email,
                                       @Field("password") String password);


    @GET("user")
    Call<Response<User>> checkAuth();

    @GET("user")
    Single<Response<User>> checkAuthRx();

    @GET("user")
    Single<ResponseBody> checkAuthResponceBody();

    @GET("user")
    Single<Response<Object>> checkAuthRxWithMultypleResponces();

    @GET("user")
    Single<WrapperResponce> checkAuthRxWithWrapperResponces();

    @GET("user")
    Call<WrapperResponce> checkAuthWithWrapperResponces();
}
