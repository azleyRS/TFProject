package com.example.rus.tfproject.network;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
        HashSet<String> pref = getCookies();
        for (String cookie: pref){
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }

    private HashSet<String> getCookies() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
        return (HashSet<String>) sharedPreferences.getStringSet(COOKIES, new HashSet<String>());
    }
}
