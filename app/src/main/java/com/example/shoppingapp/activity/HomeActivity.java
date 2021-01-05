package com.example.shoppingapp.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseActivity;
import com.example.shoppingapp.fragment.TianMaoFragment;
import com.example.shoppingapp.fragment.WhatMaiFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.vpHome)
    ViewPager mVpHome;
    ArrayList<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmentList.add(TianMaoFragment.newInstance());
        mFragmentList.add(WhatMaiFragment.newInstance());
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mVpHome.setAdapter(adapter);
    }


    class HomeViewPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> mFgList;


        public HomeViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragmentList) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mFgList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFgList.get(position);
        }

        @Override
        public int getCount() {
            return mFgList.size();
        }
    }


}