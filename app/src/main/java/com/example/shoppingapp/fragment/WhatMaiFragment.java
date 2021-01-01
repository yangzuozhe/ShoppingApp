package com.example.shoppingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.TianMaoProductInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WhatMaiFragment extends Fragment {
    @BindView(R.id.edWhatMai)
    EditText mEdWhatMai;
    @BindView(R.id.btnWhatMaiSearch)
    Button mBtnWhatMaiSearch;


    public static WhatMaiFragment newInstance() {

        Bundle args = new Bundle();

        WhatMaiFragment fragment = new WhatMaiFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waht_mai_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btnWhatMaiSearch, R.id.edWhatMai})
    public void onClick(View v) {
        final int i = v.getId();
        if (i == R.id.btnWhatMaiSearch || i == R.id.edWhatMai) {
            Intent intent = new Intent(getContext(), TianMaoProductInfoActivity.class);
            startActivity(intent);
        }
    }
}
