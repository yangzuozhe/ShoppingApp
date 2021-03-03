package com.example.shoppingapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Data.TianmaoData;
import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activity.ProductDetailInfoActivity;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.base.IntentKey;
import com.example.shoppingapp.bean.TianmaoSearchBean;

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
    //    static TianMaoProductInfoFragment fragment;
    public static final String INFO_KEY = "INFO_KEY";

    public static TianMaoProductInfoFragment newInstance(String info) {
        TianMaoProductInfoFragment fragment = new TianMaoProductInfoFragment();
        Bundle args = new Bundle();
        args.putString(INFO_KEY, info);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tian_mao_prduct_info_fragment, null);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            requestTaoBaoSearch(bundle.getString(INFO_KEY));
        }

        return view;
    }

    public void initRecyclerView(ArrayList<TianmaoSearchBean> searchBeanArrayList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
                mRvProduceInfo.setLayoutManager(manager);
                final ProductInfoAdapter adapter = new ProductInfoAdapter(getContext(),searchBeanArrayList);
                mRvProduceInfo.setAdapter(adapter);
            }
        });

    }


    /**
     * 淘宝搜索的数据
     *
     * @param searchName 想搜索的内容
     */
    private void requestTaoBaoSearch(String searchName) {
        String url = "https://list.tmall.com/search_product.htm?q=" + searchName + "&type=p&style=&cat=all&vmarket=";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String html;
                if (response.body() != null) {
                    html = response.body().string();
                    ArrayList<TianmaoSearchBean> mSearchBeanArrayList = TianmaoData.TianmaoSearch(html);
                    initRecyclerView(mSearchBeanArrayList);
                }


            }
        });
    }

    public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.MyViewHolder> {
        private ArrayList<TianmaoSearchBean> mBeanList;
        Context mContext;

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

        public ProductInfoAdapter(Context context, ArrayList<TianmaoSearchBean> beanList) {
            mContext = context;
            mBeanList = beanList;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tian_mao_product_info_recyclerview_item, null);
            final MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            TianmaoSearchBean bean = mBeanList.get(position);
            holder.tvName.setText(bean.getTitle());
            holder.tvPrice.setText(bean.getPrice());
            holder.tvSellNumber.setText(bean.getStatus());
            holder.tvStoryName.setText(bean.getShopName());
            Glide.with(mContext).load(bean.getPicture()).into(holder.ivInfoBg);
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
