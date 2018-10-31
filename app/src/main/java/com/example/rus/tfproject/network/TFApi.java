package com.example.rus.tfproject.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TFApi {
    private static final String URL = "https://fintech.tinkoff.ru/api/";

    private static TFApi tfApi;
    private final AuthEndpoint authEndpoint;

    public static synchronized TFApi getInstance(){
        if (tfApi == null){
            tfApi = new TFApi();
        }
        return tfApi;
    }

    private TFApi(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        authEndpoint = retrofit.create(AuthEndpoint.class);
    }

    public AuthEndpoint getAuthEndpoint() {
        return authEndpoint;
    }
}
