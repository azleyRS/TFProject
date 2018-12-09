package com.example.rus.tfproject.network;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.rus.tfproject.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AddCookiesInterceptor implements Interceptor {
    private static final String COOKIES = "cookies";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        //testing
        if (MyApplication.isCookies() == true){
            HashSet<String> pref = getCookies();
            Log.v("Cookies", pref.toString());
            for (String cookie: pref){
                //testing
                if (cookie.contains("anygen")){
                    builder.addHeader("cookie", cookie);
                }
                if (cookie.contains("csrf")){
                    builder.addHeader("X-CSRF-Token", cookie);
                }
            }
            builder.addHeader("Referer", "https://fintech.tinkoff.ru/");
        }


        return chain.proceed(builder.build());
    }

    private HashSet<String> getCookies() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
        return (HashSet<String>) sharedPreferences.getStringSet(COOKIES, new HashSet<String>());
    }
}
