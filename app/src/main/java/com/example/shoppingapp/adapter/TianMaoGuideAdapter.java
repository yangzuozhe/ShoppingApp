package com.example.shoppingapp.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductInfoActivity;
import com.example.shoppingapp.base.Constance;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.TianmaoGuideBean;

import java.util.List;

public class TianMaoGuideAdapter extends RecyclerView.Adapter<TianMaoGuideAdapter.GuideViewHolder> {

    private List<TianmaoGuideBean> mBeanList;
    private String mIntentType;

    public TianMaoGuideAdapter(List<TianmaoGuideBean> beanList, String intentType) {
        mBeanList = beanList;
        mIntentType = intentType;
    }

    class GuideViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public GuideViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnHomeGuideItem);
        }
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tian_mao_home_glide_item, null);
        final GuideViewHolder viewHolder = new GuideViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
        TianmaoGuideBean bean = mBeanList.get(position);
        final String title = bean.getTitle();
        holder.button.setOnClickListener(v -> {
            ARouter.getInstance().build(Constance.ACTIVITY_URL_PRODUCT_INFO)
                    .withString(IntentKey.INTENT_KEY, mIntentType)
                    .withString(IntentKey.HOME_LABEL_INFO, title)
                    .navigation();
        });
        holder.button.setText(title);
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }
}
