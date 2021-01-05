package com.example.shoppingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Data.WhatMaiData;
import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductDetailInfoActivity;
import com.example.shoppingapp.activity.ProductInfoActivity;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.WhatMaiProductBean;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WhatMaiProductInfoFragment extends BaseFragment {
    public static final String INFO_KEY = "INFO_KEY";
    @BindView(R.id.rvWmProductInfo)
    RecyclerView mRvWmProductInfo;

    public static WhatMaiProductInfoFragment newInstance(String infoKey) {

        Bundle args = new Bundle();
        args.putString(INFO_KEY, infoKey);
        WhatMaiProductInfoFragment fragment = new WhatMaiProductInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.what_mai_product_info_fragment, null);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            String info = getArguments().getString(INFO_KEY);
            requestWhatMaiInfo(info);
        }
        return view;
    }


    private void requestWhatMaiInfo(String info) {
        String url = "https://search.smzdm.com/?c=home&s=" + info + "&v=b";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    String html = response.body().string();
                    ArrayList<WhatMaiProductBean> mBeanList = WhatMaiData.whatMaiProductList(html);
                    onBindView(mBeanList);
                }
            }
        });
    }

    private void onBindView(ArrayList<WhatMaiProductBean> beanList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyAdapter adapter = new MyAdapter(beanList);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
                mRvWmProductInfo.setLayoutManager(manager);
                mRvWmProductInfo.setAdapter(adapter);
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        ArrayList<WhatMaiProductBean> mBeanList;


        class MyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.ivWmInfo)
            ImageView mIvWmInfo;
            @BindView(R.id.tvWmTitle)
            TextView mTvWmTitle;
            @BindView(R.id.tvWmPrice)
            TextView mTvWmPrice;
            @BindView(R.id.tvWmMark)
            TextView mTvWmMark;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public MyAdapter(ArrayList<WhatMaiProductBean> beanList) {
            mBeanList = beanList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.what_mai_product_info_recyclerview_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            WhatMaiProductBean bean = mBeanList.get(position);
            holder.mTvWmTitle.setText(bean.getTitle());
            holder.mTvWmMark.setText(bean.getMark());
            holder.mTvWmPrice.setText(bean.getPrice());
            Glide.with(holder.mIvWmInfo.getContext()).load(bean.getPictureUrl()).into(holder.mIvWmInfo);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ProductDetailInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IntentKey.BEAN_INFO,bean);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mBeanList.size();
        }


    }


}
