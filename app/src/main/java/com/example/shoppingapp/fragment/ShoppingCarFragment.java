package com.example.shoppingapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseFragment;

public class ShoppingCarFragment extends BaseFragment {
    public static ShoppingCarFragment newInstance() {
        ShoppingCarFragment fragment = new ShoppingCarFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.shopping_car_fragment, null);
        return view;
    }
}
