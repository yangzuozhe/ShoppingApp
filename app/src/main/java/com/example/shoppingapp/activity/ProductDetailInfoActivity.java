package com.example.shoppingapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.Constance;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.BaseBean;
import com.example.shoppingapp.bean.TianmaoSearchBean;
import com.example.shoppingapp.bean.WhatMaiProductBean;
import com.example.shoppingapp.fragment.ShoppingCarFragment;
import com.example.shoppingapp.sql.SQLiteUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品的详细页面的信息
 */
@Route(path = Constance.ACTIVITY_URL_PRODUCT_DETAIL_INFO)
public class ProductDetailInfoActivity extends BaseActivity {
    String mIntentKey;
    BaseBean mBean;
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
    @BindView(R.id.tvAddShoppingCar)
    TextView mTvAddShoppingCar;
    SQLiteUtils mSqLiteUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_info);
        mSqLiteUtils = new SQLiteUtils(ProductDetailInfoActivity.this);
        ButterKnife.bind(this);
        getData();
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIntentKey = bundle.getString(IntentKey.INTENT_KEY);
            mBean = (BaseBean) bundle.getSerializable(IntentKey.BEAN_INFO);
            if (mBean instanceof TianmaoSearchBean) {
                onBindTianMao((TianmaoSearchBean) mBean);
            } else if (mBean instanceof WhatMaiProductBean) {
                onBindWhatMai((WhatMaiProductBean) mBean);
            }
        }
    }

    public void onBindTianMao(TianmaoSearchBean bean) {
        Glide.with(ProductDetailInfoActivity.this).load(bean.getPicture()).into(mIvDePicture);
        mTvDeTitle.setText(bean.getTitle());
        mTvDePrice.setText(bean.getPrice());
        mTvDeMonthSell.setText(bean.getStatus());
        mTvInfoText.setText(bean.getShopName());
    }

    public void onBindWhatMai(WhatMaiProductBean bean) {
        Glide.with(ProductDetailInfoActivity.this).load(bean.getPicture()).into(mIvDePicture);
        mTvDeTitle.setText(bean.getTitle());
        mTvDePrice.setText(bean.getPrice());
        mTvDeMonthSell.setVisibility(View.GONE);
        mTvInfoText.setText(bean.getMark());
    }

    @OnClick({R.id.tvAddShoppingCar,R.id.btnShopping})
    public void onClick(View view) {
        if (view.getId() == R.id.tvAddShoppingCar) {
            //插入数据
            mSqLiteUtils.insertData("Shopping", mBean);
            Toast.makeText(view.getContext(),"成功加入购物车",Toast.LENGTH_SHORT).show();
        }else if (view.getId() == R.id.btnShopping){
            ShoppingCarFragment.newInstance().show(getSupportFragmentManager(),"shoppingCar");
        }
    }
}