package com.example.shoppingapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductInfoActivity;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.widget.SearchBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WhatMaiFragment extends BaseFragment {
    @BindView(R.id.sbWhaiMai)
    SearchBar mSbWhaiMai;
    @BindView(R.id.vvWhatMai)
    VideoView mVvWhatMai;
    MediaController mMediaController;
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
        return view;
    }
    public void initView(){
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

    @Override
    public void onStop() {
        super.onStop();
        mVvWhatMai.pause();
    }
}
