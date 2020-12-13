package com.example.shoppingapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.fragment.TianMaoSearchGliderFragment;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        setContentView(R.layout.activity_tian_mao_prduct_info);
        initViews();
        //
        showSoftInputFromWindow(mEtTianMaoSearch);
    }

    public void initViews() {
        ButterKnife.bind(this);
        replaceFragment(new TianMaoSearchGliderFragment(), R.id.flProductionView, false);
    }



}