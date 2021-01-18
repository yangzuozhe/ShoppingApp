package com.example.shoppingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return view;
    }
    public void initView(){
        mSbWhaiMai.setOnclick(() -> {
            Intent intent = new Intent(getContext(), ProductInfoActivity.class);
            intent.putExtra(IntentKey.INTENT_KEY, IntentKey.WHAT_MAI_KEY);
            startActivity(intent);
        });
    }

}
