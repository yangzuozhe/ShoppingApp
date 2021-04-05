package com.example.shoppingapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.Data.TianmaoData;
import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductInfoActivity;
import com.example.shoppingapp.adapter.TianMaoGuideAdapter;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.TianmaoGuideBean;
import com.example.shoppingapp.widget.SearchBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WhatMaiFragment extends BaseFragment {
    @BindView(R.id.sbWhaiMai)
    SearchBar mSbWhaiMai;
    @BindView(R.id.vvWhatMai)
    VideoView mVvWhatMai;
    @BindView(R.id.rvWhatMaiGuide)
    RecyclerView mRvWhatMaiGuide;
    MediaController mMediaController;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public static WhatMaiFragment newInstance() {

        Bundle args = new Bundle();

        WhatMaiFragment fragment = new WhatMaiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waht_mai_fragment, null);
        ButterKnife.bind(this, view);
        initView();
        initVideoView();
        requestTianmaoHome();
        return view;
    }

    public void initView() {
        mSbWhaiMai.setOnclick(() -> {
            Intent intent = new Intent(getContext(), ProductInfoActivity.class);
            intent.putExtra(IntentKey.INTENT_KEY, IntentKey.WHAT_MAI_KEY);
            startActivity(intent);
        });
    }

    /**
     * 初始化视频和视频控制器
     */
    private void initVideoView() {
        //进度控制器
        mMediaController = new MediaController(getContext());
        //视频view
        mVvWhatMai.setMediaController(mMediaController);
        //网络资源的视频
        mVvWhatMai.setVideoURI(Uri.parse("https://vd3.bdstatic.com/mda-kedw03n0xmy3wrwn/v1-cae/hd/mda-kedw03n0xmy3wrwn.mp4"));

    }

    /**
     * 请求引导界面的数据
     */
    public void requestTianmaoHome() {
        String url = "https://www.tmall.com/?page_offline=true";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String html;
                if (response.body() != null) {
                    html = response.body().string();
                    ArrayList<TianmaoGuideBean> beanArrayList = TianmaoData.TianMaoHome(html);
                    mHandler.post(() -> initRecyclerView(beanArrayList));

                }


            }
        });
    }

    private void initRecyclerView(ArrayList<TianmaoGuideBean> beanArrayList) {
        if (beanArrayList == null){
            return;
        }
        TianMaoGuideAdapter adapter = new TianMaoGuideAdapter(beanArrayList,IntentKey.WHAT_MAI_KEY);
        mRvWhatMaiGuide.setLayoutManager(new GridLayoutManager(getContext(),4));
        mRvWhatMaiGuide.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        mVvWhatMai.pause();
    }
}
