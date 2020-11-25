package com.example.shoppingapp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    int cacheSize = 10 * 1024 * 1024;
    public static void requestData(String url, okhttp3.Callback callback) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //一旦超过该时间，就中断不再加载
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
