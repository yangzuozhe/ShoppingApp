package com.example.shoppingapp.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.TianmaoBannerBean;
import com.example.shoppingapp.bean.TianmaoGuideBean;
import com.example.shoppingapp.viewholder.TianmaoBannerViewHolder;

import java.util.List;

public class TianMaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //轮播图的viewHolder标志
    final static int BANNER = 0;
    //引导界面的viewHolder标志
    final static int GUIDE = 1;
    //猜你喜欢的viewHolder标志
    final static int GUESS = 2;
    List<List> mObjectList;


    public TianMaoAdapter(List<List> objectList) {
        mObjectList = objectList;
    }


    @Override
    public int getItemViewType(int position) {
        //这里注意是两个get，因为第一次get是获取其中一个ViewHolder的List集合
        Object o = mObjectList.get(position).get(position);
        if (o instanceof TianmaoGuideBean) {
            return GUIDE;
        } else if (o instanceof TianmaoBannerBean) {
            return BANNER;
        } else {
            return GUESS;
        }
    }

    class GlideViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRvHomeGuide;

        public GlideViewHolder(@NonNull View itemView) {
            super(itemView);
            mRvHomeGuide = itemView.findViewById(R.id.rvHomeGuideViewHolder);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ConvenientBanner<TianmaoBannerBean> mcbTianMaoBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mcbTianMaoBanner = itemView.findViewById(R.id.cbTianMaoBanner);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == GUIDE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tian_mao_home_glide_viewholder, null);
            return new GlideViewHolder(view);
        } else if (viewType == BANNER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tian_mao_banner_viewholder, null);
            return new BannerViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //通过 instanceof 判断当前的 viewHolder是哪一个
        if (holder instanceof GlideViewHolder) {
            //这里我先将数据源里的某一块viewHolder所需要用到的数据提取出来
            //比如这里，我提取出了 List<TianmaoGuideBean> 这个数据，待会要给另一个RecyclerView使用当数据
            final List<TianmaoGuideBean> beanList = mObjectList.get(position);
            initTianmaoGuide(((GlideViewHolder) holder).mRvHomeGuide, beanList);
        } else if (holder instanceof BannerViewHolder) {
            final List<TianmaoBannerBean> beanList = mObjectList.get(position);
            startTianMaoBanner(((BannerViewHolder) holder).mcbTianMaoBanner, beanList);
        }
    }


    @Override
    public int getItemCount() {
        return mObjectList.size();
    }

    public void startTianMaoBanner(ConvenientBanner<TianmaoBannerBean> convenientBanner, List<TianmaoBannerBean> priceBeans) {
        if (priceBeans != null) {
            convenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new TianmaoBannerViewHolder();
                }
            }, priceBeans);
        }
        convenientBanner.startTurning(1000);
    }

    public void initTianmaoGuide(RecyclerView recyclerView, List<TianmaoGuideBean> beanList) {
        final GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 4);
        final TianMaoGuideAdapter adapter = new TianMaoGuideAdapter(beanList, IntentKey.TIAN_MAO_KEY);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

}