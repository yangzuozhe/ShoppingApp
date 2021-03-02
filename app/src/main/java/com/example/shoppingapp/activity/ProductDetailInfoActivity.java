package com.example.shoppingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.TianmaoSearchBean;
import com.example.shoppingapp.bean.WhatMaiProductBean;

import org.w3c.dom.Text;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品的详细页面的信息
 */
public class ProductDetailInfoActivity extends BaseActivity {
    String mIntentKey;
    Serializable mBean;
    /**
     * 商品图片
     */
    @BindView(R.id.ivDePicture)
    ImageView mIvDePicture;
    /**
     * 商品的名称
     */
    @BindView(R.id.tvDeTitle)
    TextView mTvDeTitle;
    /**
     * 商品的价格
     */
    @BindView(R.id.tvDePrice)
    TextView mTvDePrice;
    /**
     * 商品的月销售
     */
    @BindView(R.id.tvDeMonthSell)
    TextView mTvDeMonthSell;
    /**
     * 商品的介绍
     */
    @BindView(R.id.tvInfoText)
    TextView mTvInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_info);
        ButterKnife.bind(this);
        getData();
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIntentKey = bundle.getString(IntentKey.INTENT_KEY);
            mBean = bundle.getSerializable(IntentKey.BEAN_INFO);
            if (mBean instanceof TianmaoSearchBean) {
                onBindTianMao((TianmaoSearchBean) mBean);
            } else if (mBean instanceof WhatMaiProductBean) {
                onBindWhatMai((WhatMaiProductBean) mBean);
            }
        }
    }

    public void onBindTianMao(TianmaoSearchBean bean) {
        Glide.with(ProductDetailInfoActivity.this).load(bean.getImageUrl()).into(mIvDePicture);
        mTvDeTitle.setText(bean.getTitle());
        mTvDePrice.setText(bean.getPrice());
        mTvDeMonthSell.setText(bean.getStatus());
        mTvInfoText.setText(bean.getShopName());
    }

    public void onBindWhatMai(WhatMaiProductBean bean) {
        Glide.with(ProductDetailInfoActivity.this).load(bean.getPictureUrl()).into(mIvDePicture);
        mTvDeTitle.setText(bean.getTitle());
        mTvDePrice.setText(bean.getPrice());
        mTvDeMonthSell.setVisibility(View.GONE);
        mTvInfoText.setText(bean.getMark());
    }

    @OnClick(R.id.tvAddShoppingCar)
    public void onClick(View view){
        if (view.getId() == R.id.tvAddShoppingCar){

        }
    }
}