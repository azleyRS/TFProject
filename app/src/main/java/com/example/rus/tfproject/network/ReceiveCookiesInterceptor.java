package com.example.rus.tfproject.network;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.rus.tfproject.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

class ReceiveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()){
            //not sure here
            HashSet<String> cookies = new HashSet<>(response.headers("Set-Cookie"));
            setCookies(cookies);
        }
        return response;
    }

    private void setCookies(HashSet<String> cookies) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("cookies", cookies).apply();
    }
}
