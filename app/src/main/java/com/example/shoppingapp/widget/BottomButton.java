package com.example.shoppingapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.shoppingapp.R;

/**
 * 底部按钮控件
 */
public class BottomButton extends LinearLayout {
    private RelativeLayout btnHome;
    private RelativeLayout btnShoppingCar;
    View.OnClickListener mLister;

    public BottomButton(Context context) {
        this(context, null);
    }

    public BottomButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.bottom_button_view, this);
        initView();
        setBtnHomeSelect(true);
    }

    private void initView() {
        btnHome = findViewById(R.id.btnHome);
        btnShoppingCar = findViewById(R.id.btnShoppingCar);
    }

    public void setBtnHomeSelect(boolean isSelect) {
        btnHome.setSelected(isSelect);
        btnShoppingCar.setSelected(!isSelect);
    }

    public void setBtnShoppingCarSelect(boolean isSelect) {
        btnShoppingCar.setSelected(isSelect);
        btnHome.setSelected(!isSelect);
    }

    public void setOnClickLister(View.OnClickListener lister) {
        btnShoppingCar.setOnClickListener(lister);
        btnHome.setOnClickListener(lister);
    }

}
