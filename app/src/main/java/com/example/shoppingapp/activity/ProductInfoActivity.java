package com.example.shoppingapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.Constance;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.base.StringUtils;
import com.example.shoppingapp.dialog.LoadingDialog;
import com.example.shoppingapp.fragment.TianMaoProductInfoFragment;
import com.example.shoppingapp.fragment.WhatMaiProductInfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品搜索结果页面的信息
 */
@Route(path = Constance.ACTIVITY_URL_PRODUCT_INFO)
public class ProductInfoActivity extends BaseActivity {
    @BindView(R.id.etTianMaoSearch)
    EditText mEtTianMaoSearch;
    @BindView(R.id.btnTianMaoSearch)
    Button mBtnTianMaoSearch;
    @BindView(R.id.flProductionView)
    FrameLayout mFlProductionView;
    @BindView(R.id.clGliderBg)
    ConstraintLayout mClGliderBg;
    /**
     * 判断当前页面时那种平台搜索
     */
    private String mIntentKey;
    /**
     * 是否是标签
     */
    private String mLabelInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntentKey = getIntent().getStringExtra(IntentKey.INTENT_KEY);
        mLabelInfo = getIntent().getStringExtra(IntentKey.HOME_LABEL_INFO);
        setContentView(R.layout.tian_mao_prduct_info_activity);
        initViews();
        initEdiText();
        replaceProductInfoFragment(mLabelInfo);
    }

    private void initEdiText() {
        mEtTianMaoSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtTianMaoSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                replaceProductInfoFragment(v.getText().toString());
            }
            return false;
        });
        if (StringUtils.isEmploy(mLabelInfo)) {
            showSoftInputFromWindow(mEtTianMaoSearch);
        } else {
            mEtTianMaoSearch.setText(mLabelInfo);
            mEtTianMaoSearch.setSelection(mLabelInfo.length());
            mClGliderBg.setFocusable(true);
            mClGliderBg.setFocusableInTouchMode(true);
            mClGliderBg.requestFocus();
        }
    }

    private void initViews() {
        ButterKnife.bind(this);
        setBackGround();
    }

    private void setBackGround() {
        if (mIntentKey.equals(IntentKey.TIAN_MAO_KEY)) {
            mClGliderBg.setBackgroundResource(R.drawable.tianmao_glide_bg);
        } else if (mIntentKey.equals(IntentKey.WHAT_MAI_KEY)) {
            mClGliderBg.setBackgroundResource(R.drawable.whatbuy_glide_bg);
        } else {
            mClGliderBg.setBackgroundResource(0);
        }
    }

    @OnClick(R.id.btnTianMaoSearch)
    public void onClick(View view) {
        if (view.getId() == R.id.btnTianMaoSearch) {
            hideSoftInputFromWindow(mEtTianMaoSearch);
            replaceProductInfoFragment(mEtTianMaoSearch.getText().toString());
        }
    }

    Fragment mShoppingFragment;

    private void replaceProductInfoFragment(String searchText) {
        if (StringUtils.isEmploy(searchText)) {
            return;
        }
        final LoadingDialog dialog = new LoadingDialog(this);
        dialog.show();
        if (mShoppingFragment == null) {
            if (mIntentKey.equals(IntentKey.TIAN_MAO_KEY)) {
                mShoppingFragment = TianMaoProductInfoFragment.newInstance(searchText, dialog);
                replaceFragment(mShoppingFragment, R.id.flProductionView, false);
            } else if (mIntentKey.equals(IntentKey.WHAT_MAI_KEY)) {
                mShoppingFragment = WhatMaiProductInfoFragment.newInstance(searchText, dialog);
                replaceFragment(mShoppingFragment, R.id.flProductionView, false);
            }
        } else {
            if (mShoppingFragment instanceof TianMaoProductInfoFragment) {
                    ((TianMaoProductInfoFragment) mShoppingFragment).requestTaoBaoSearch(searchText,dialog);
            } else if (mShoppingFragment instanceof WhatMaiProductInfoFragment) {
                    ((WhatMaiProductInfoFragment) mShoppingFragment).requestWhatMaiInfo(searchText,dialog);
            }
        }

    }

}