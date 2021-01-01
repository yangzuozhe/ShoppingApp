package com.example.shoppingapp.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.StringUtils;
import com.example.shoppingapp.fragment.TianMaoProductInfoFragment;
import com.example.shoppingapp.fragment.TianMaoSearchGliderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TianMaoProductInfoActivity extends BaseActivity {
    @BindView(R.id.etTianMaoSearch)
    EditText mEtTianMaoSearch;
    @BindView(R.id.btnTianMaoSearch)
    Button mBtnTianMaoSearch;
    @BindView(R.id.flProductionView)
    FrameLayout mFlProductionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            if (!StringUtils.isEmploy(mEtTianMaoSearch.getText().toString())) {
                replaceFragment(TianMaoProductInfoFragment.newInstance(mEtTianMaoSearch.getText().toString()), R.id.flProductionView, false);
            }
        }
    }


}