package com.example.shoppingapp.activity;

import android.os.Bundle;

import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.TianmaoData;
import com.example.shoppingapp.base.BaseActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s = "https://list.tmall.com/search_product.htm?q=%D0%AC%D7%D3&type=p&vmarket=&spm=875.7931836%2FB.a2227oh.d100&from=mallfp..pc_1_searchbutton";
        OkHttpUtils.requestData(s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                TianmaoData.demo(response.body().string());
            }
        });

    }






}