package com.example.rus.tfproject.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TFApi {
    private static final String URL = "https://fintech.tinkoff.ru/api/";
    // try later with 2
    private static final int TIMEOUT_IN_SECONDS = 5;

    private static TFApi tfApi;
    private final AuthEndpoint authEndpoint;

    public static synchronized TFApi getInstance(){
        if (tfApi == null){
            tfApi = new TFApi();
        }
        return tfApi;
    }

    private TFApi(){
        final OkHttpClient httpClient = buildOkHttpClient();
        final Retrofit retrofit = buildRetrofitClient(httpClient);

        authEndpoint = retrofit.create(AuthEndpoint.class);
    }

    private Retrofit buildRetrofitClient(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceiveCookiesInterceptor())
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    public AuthEndpoint getAuthEndpoint() {
        return authEndpoint;
    }
}
