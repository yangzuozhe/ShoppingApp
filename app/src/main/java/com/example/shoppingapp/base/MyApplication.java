package com.example.shoppingapp.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getIntent() {
        return mContext;
    }
}
