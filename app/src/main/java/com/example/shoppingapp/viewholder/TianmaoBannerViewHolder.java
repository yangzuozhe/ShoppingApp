package com.example.shoppingapp.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.shoppingapp.bean.TianmaoBannerBean;

public class TianmaoBannerViewHolder implements Holder<TianmaoBannerBean> {
    ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mImageView.setLayoutParams(params);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, TianmaoBannerBean data) {
        Glide.with(context).load(data.getImageUrl()).into(mImageView);
    }
}
