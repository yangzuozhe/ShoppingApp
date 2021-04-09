package com.example.shoppingapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.base.Constance;
import com.example.shoppingapp.dialog.GuideDialog;
import com.example.shoppingapp.fragment.HomeFragment;
import com.example.shoppingapp.fragment.ShoppingCarFragment;
import com.example.shoppingapp.widget.BottomButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 */
@Route(path = Constance.ACTIVITY_URL_HOME)
public class HomeActivity extends BaseActivity {
    @BindView(R.id.flHome)
    FrameLayout mFlHome;
    @BindView(R.id.bottomButton)
    BottomButton mBottomButton;
    private Fragment mHomeFragment;
    private Fragment mShoppingCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomButton();
        addHome();
        initGuideDialog();
    }

    /**
     * 添加首页
     */
    private void addHome() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        addFragmentWithoutClearOld(mShoppingCar, mHomeFragment, R.id.flHome);
    }

    private void addShoppingCar() {
        if (mShoppingCar == null) {
            mShoppingCar = ShoppingCarFragment.newInstance();
        }
        addFragmentWithoutClearOld(mHomeFragment, mShoppingCar, R.id.flHome);
    }

    /**
     * 初始话底部按钮控件的操作
     */
    private void initBottomButton() {
        mBottomButton.setOnClickLister(v -> {
            int i = v.getId();
            if (i == R.id.btnHome) {
                addHome();
                mBottomButton.setBtnHomeSelect(true);
            } else if (i == R.id.btnShoppingCar) {
                addShoppingCar();
                mBottomButton.setBtnShoppingCarSelect(true);
            }

        });
    }

    private void initGuideDialog(){
        SharedPreferences preferences = getSharedPreferences("guide_dialog",MODE_PRIVATE);
        if (preferences != null){
            if (!preferences.getBoolean("isShowGuide",false)){
                GuideDialog dialog = new GuideDialog(this);
                dialog.show();
            }
        }

    }


}