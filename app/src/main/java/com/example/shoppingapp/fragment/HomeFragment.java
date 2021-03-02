package com.example.shoppingapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.vpHome)
    ViewPager mVpHome;
    ArrayList<Fragment> mFragmentList = new ArrayList<>();
    HomeViewPagerAdapter mAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, null);
        ButterKnife.bind(this, view);
        initViewPager();
        return view;
    }

    private void initViewPager() {

        mFragmentList.add(TianMaoFragment.newInstance());
        mFragmentList.add(WhatMaiFragment.newInstance());

        if (getFragmentManager() != null) {
            mAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), mFragmentList);
        }
        mVpHome.setAdapter(mAdapter);
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
