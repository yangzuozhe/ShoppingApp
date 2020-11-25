package com.example.shoppingapp.activity;

import android.os.Bundle;

import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.TianmaoData;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.bean.TianmaoSearchBean;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends BaseActivity {
    /**
     * 搜索得到的数据
     */
    ArrayList<TianmaoSearchBean> mSearchBeanArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 淘宝搜索的数据
     *
     * @param searchName 想搜索的内容
     */
    public void requestTaoBaoSearch(String searchName) {
        String url = "https://list.tmall.com/search_product.htm?q=" + searchName + "&type=p&style=&cat=all&vmarket=";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String html;
                if (response.body() != null) {
                    html = response.body().string();
                    mSearchBeanArrayList = TianmaoData.TianmaoSearch(html);

                }

            }
        });
    }


}