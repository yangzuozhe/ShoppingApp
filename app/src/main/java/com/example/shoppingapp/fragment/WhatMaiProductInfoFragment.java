package com.example.shoppingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Data.WhatMaiData;
import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductDetailInfoActivity;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.WhatMaiProductBean;
import com.example.shoppingapp.dialog.LoadingDialog;

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
    WhatMaiProductInfoAdapter mWhatMaiProductInfoAdapter;
    ArrayList<WhatMaiProductBean> mBeanList = new ArrayList<>();
    private static LoadingDialog mLoadingDialog;

    Handler mHandler = new Handler(Looper.getMainLooper());

    public static WhatMaiProductInfoFragment newInstance(String infoKey, LoadingDialog dialog) {

        Bundle args = new Bundle();
        args.putString(INFO_KEY, infoKey);
        WhatMaiProductInfoFragment fragment = new WhatMaiProductInfoFragment();
        fragment.setArguments(args);
        setLoadingDialog(dialog);
        return fragment;
    }

    public static void setLoadingDialog(LoadingDialog loadingDialog) {
        mLoadingDialog = loadingDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.what_mai_product_info_fragment, null);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            String info = getArguments().getString(INFO_KEY);
            requestWhatMaiInfo(info, mLoadingDialog);
        }
        return view;
    }


    public void requestWhatMaiInfo(String info, LoadingDialog loadingDialog) {
        String url = "https://search.smzdm.com/?c=home&s=" + info + "&v=b";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(() -> {
                    loadingDialog.dismiss();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    mHandler.post(() -> {
                        loadingDialog.dismiss();
                    });
                    String html = response.body().string();
                    mBeanList.clear();
                    mBeanList.addAll(WhatMaiData.whatMaiProductList(html));
                    onBindView(mBeanList);
                }
            }
        });
    }

    private void onBindView(ArrayList<WhatMaiProductBean> beanList) {
        mHandler.post(() -> {
            if (mWhatMaiProductInfoAdapter == null) {
                mWhatMaiProductInfoAdapter = new WhatMaiProductInfoAdapter(beanList);
                mRvWmProductInfo.setAdapter(mWhatMaiProductInfoAdapter);
            }
            if (beanList.isEmpty()) {
                mRvWmProductInfo.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRvWmProductInfo.setLayoutManager(manager);
            }
            mWhatMaiProductInfoAdapter.notifyDataSetChanged();
        });

    }

    class WhatMaiProductInfoAdapter extends RecyclerView.Adapter {
        ArrayList<WhatMaiProductBean> mBeanList;
        public static final int NO_INFO = 11111;

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

        public WhatMaiProductInfoAdapter(ArrayList<WhatMaiProductBean> beanList) {
            mBeanList = beanList;
        }

        class NoInfoViewHolder extends RecyclerView.ViewHolder {

            public NoInfoViewHolder(@NonNull View itemView) {
                super(itemView);
            }

        }
        @Override
        public int getItemViewType(int position) {
            if (mBeanList.isEmpty()) {
                return NO_INFO;
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == NO_INFO) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.no_info_viewholder, null);
                return new NoInfoViewHolder(view);
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.what_mai_product_info_recyclerview_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof MyViewHolder) {
                MyViewHolder holder = (MyViewHolder) viewHolder;
                WhatMaiProductBean bean = mBeanList.get(position);
                holder.mTvWmTitle.setText(bean.getTitle());
                holder.mTvWmMark.setText(bean.getMark());
                holder.mTvWmPrice.setText(bean.getPrice());
                Glide.with(holder.mIvWmInfo.getContext()).load(bean.getPicture()).into(holder.mIvWmInfo);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ProductDetailInfoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(IntentKey.BEAN_INFO, bean);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            if (mBeanList.isEmpty()) {
                return 1;
            }
            return mBeanList.size();
        }


    }


}
