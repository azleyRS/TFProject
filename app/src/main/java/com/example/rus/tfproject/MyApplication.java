package com.example.rus.tfproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class MyApplication extends Application {
    private static Context context;
    private static boolean cookies = false;

    public static boolean isCookies() {
        return cookies;
    }

    public static void setCookies(boolean cookies) {
        MyApplication.cookies = cookies;
    }

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        //testing
        if(PreferenceManager.getDefaultSharedPreferences(this).contains("cookies")){
            setCookies(true);
            Log.v("MyAppCookiesCheck", "containes cookies");
        }
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
