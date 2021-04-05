package com.example.shoppingapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Data.TianmaoData;
import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductDetailInfoActivity;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.TianmaoSearchBean;
import com.example.shoppingapp.dialog.LoadingDialog;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TianMaoProductInfoFragment extends BaseFragment {
    @BindView(R.id.rvProductInfo)
    RecyclerView mRvProduceInfo;

    private static LoadingDialog mLoadingDialog;
    private ProductInfoAdapter mProductInfoAdapter;
    private ArrayList<TianmaoSearchBean> mSearchBeanArrayList = new ArrayList<>();
    //    static TianMaoProductInfoFragment fragment;
    public static final String INFO_KEY = "INFO_KEY";
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public static TianMaoProductInfoFragment newInstance(String info, LoadingDialog dialog) {
        TianMaoProductInfoFragment fragment = new TianMaoProductInfoFragment();
        Bundle args = new Bundle();
        args.putString(INFO_KEY, info);
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
        final View view = inflater.inflate(R.layout.tian_mao_prduct_info_fragment, null);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            requestTaoBaoSearch(bundle.getString(INFO_KEY),mLoadingDialog);
        }

        return view;
    }


    public void initRecyclerView(ArrayList<TianmaoSearchBean> searchBeanArrayList) {
        mHandler.post(() -> {
            if (mProductInfoAdapter == null){
                mProductInfoAdapter =new ProductInfoAdapter(getContext(), searchBeanArrayList);
                mRvProduceInfo.setAdapter(mProductInfoAdapter);
            }
            if (!mSearchBeanArrayList.isEmpty()){
                GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
                mRvProduceInfo.setLayoutManager(manager);
            }else {
                mRvProduceInfo.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            mProductInfoAdapter.notifyDataSetChanged();
        });
    }


    /**
     * 淘宝搜索的数据
     *
     * @param searchName 想搜索的内容
     */
    public void requestTaoBaoSearch(String searchName,LoadingDialog dialog) {
        String url = "https://list.tmall.com/search_product.htm?q=" + searchName + "&type=p&style=&cat=all&vmarket=";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(() -> {
                    dialog.dismiss();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String html;
                mHandler.post(() -> {
                    dialog.dismiss();
                });
                if (response.body() != null) {
                    html = response.body().string();
                    mSearchBeanArrayList.clear();
                    mSearchBeanArrayList.addAll(TianmaoData.TianmaoSearch(html));
                    initRecyclerView(mSearchBeanArrayList);
                }


            }
        });
    }

    public class ProductInfoAdapter extends RecyclerView.Adapter {
        private ArrayList<TianmaoSearchBean> mBeanList;
        Context mContext;
        public static final int NO_INFO = 11111;


        class MyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.ivInfoBg)
            ImageView ivInfoBg;
            @BindView(R.id.tvPrice)
            TextView tvPrice;
            @BindView(R.id.tvName)
            TextView tvName;
            @BindView(R.id.tvStoryName)
            TextView tvStoryName;
            @BindView(R.id.tvSellNumber)
            TextView tvSellNumber;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        class NoInfoViewHolder extends RecyclerView.ViewHolder {
            public NoInfoViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }


        public ProductInfoAdapter(Context context, ArrayList<TianmaoSearchBean> beanList) {
            mContext = context;
            mBeanList = beanList;
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
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_info_viewholder, null);
                NoInfoViewHolder viewHolder = new NoInfoViewHolder(view);
                return viewHolder;
            }
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tian_mao_product_info_recyclerview_item, null);
            final MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof MyViewHolder) {
                MyViewHolder holder = (MyViewHolder) viewHolder;
                TianmaoSearchBean bean = mBeanList.get(position);
                holder.tvName.setText(bean.getTitle());
                holder.tvPrice.setText(bean.getPrice());
                holder.tvSellNumber.setText(bean.getStatus());
                holder.tvStoryName.setText(bean.getShopName());
                Glide.with(mContext).load(bean.getPicture()).into(holder.ivInfoBg);
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), ProductDetailInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IntentKey.BEAN_INFO, bean);
                    intent.putExtras(bundle);
                    startActivity(intent);
                });
            }
        }


        @Override
        public int getItemCount() {
            if (mBeanList.size() == 0) {
                return 1;
            }
            return mBeanList.size();
        }


    }

}
