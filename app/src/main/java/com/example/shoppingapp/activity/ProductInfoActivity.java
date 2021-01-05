package com.example.shoppingapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.base.StringUtils;
import com.example.shoppingapp.fragment.TianMaoProductInfoFragment;
import com.example.shoppingapp.fragment.TianMaoSearchGliderFragment;
import com.example.shoppingapp.fragment.WhatMaiProductInfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品搜索结果页面的信息
 */
public class ProductInfoActivity extends BaseActivity {
    @BindView(R.id.etTianMaoSearch)
    EditText mEtTianMaoSearch;
    @BindView(R.id.btnTianMaoSearch)
    Button mBtnTianMaoSearch;
    @BindView(R.id.flProductionView)
    FrameLayout mFlProductionView;
    /**
     * 判断当前页面时那种平台搜索
     */
    private String mIntentKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntentKey = getIntent().getStringExtra(IntentKey.INTENT_KEY);
        setContentView(R.layout.tian_mao_prduct_info_activity);
        initViews();
        //
        showSoftInputFromWindow(mEtTianMaoSearch);
    }

    public void initViews() {
        ButterKnife.bind(this);
        replaceFragment(new TianMaoSearchGliderFragment(), R.id.flProductionView, false);
    }

    @OnClick(R.id.btnTianMaoSearch)
    public void onClick(View view) {
        if (view.getId() == R.id.btnTianMaoSearch) {
            if (StringUtils.isEmploy(mEtTianMaoSearch.getText().toString())) {
                return;
            }
            if (mIntentKey.equals(IntentKey.TIAN_MAO_KEY)) {
                replaceFragment(TianMaoProductInfoFragment.newInstance(mEtTianMaoSearch.getText().toString()), R.id.flProductionView, false);
            } else if (mIntentKey.equals(IntentKey.WHAT_MAI_KEY)) {
                replaceFragment(WhatMaiProductInfoFragment.newInstance(mEtTianMaoSearch.getText().toString()), R.id.flProductionView, false);
            }

        }
    }


}