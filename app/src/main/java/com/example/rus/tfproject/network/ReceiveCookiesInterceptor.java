package com.example.rus.tfproject.network;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.rus.tfproject.MyApplication;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

class ReceiveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()){
            //not sure here
            //HashSet<String> cookies = new HashSet<>(response.headers("Set-Cookie"));
            HashSet<String> cookies = new HashSet<>();

            for (String cookieHeader : response.headers("Set-Cookie")) {
                /*if (cookieHeader.contains("anygen")){
                    cookies.add(cookieHeader);
                }*/
                cookies.add(cookieHeader);
            }

            //for a time
            if (MyApplication.isCookies() == false){
                setCookies(cookies);
            }

            //setCookies(cookies);

        }
        return response;
    }

    private void setCookies(HashSet<String> cookies) {
        //MyApplication.setCookies(true);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("cookies", cookies).apply();
    }
}
