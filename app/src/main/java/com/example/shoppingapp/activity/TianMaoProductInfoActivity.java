package com.example.shoppingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.fragment.TianMaoSearchGliderFragment;

import butterknife.BindView;

public class TianMaoProductInfoActivity extends BaseActivity {
    @BindView(R.id.etTianMaoSearch)
    EditText mEtTianMaoSearch;
    @BindView(R.id.btnTianMaoSearch)
    Button mBtnTianMaoSearch;
    @BindView(R.id.flProductionView)
    FrameLayout mFlProductionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_mao_prduct_info);
        initViews();
    }

    public void initViews(){
        replaceFragment(new TianMaoSearchGliderFragment(),R.id.flProductionView);
        
    }

}