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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.OkHttpUtils;
import com.example.shoppingapp.R;
import com.example.shoppingapp.TianmaoData;
import com.example.shoppingapp.activity.TianMaoProductInfoActivity;
import com.example.shoppingapp.adapter.TianMaoAdapter;
import com.example.shoppingapp.base.BaseFragment;
import com.example.shoppingapp.bean.TianmaoBannerBean;
import com.example.shoppingapp.bean.TianmaoGuideBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TianMaoFragment extends BaseFragment {

    /**
     * 引导界面的数据
     */
    ArrayList<TianmaoGuideBean> mHomeBeanList;


    @BindView(R.id.rvHomeGuide)
    RecyclerView mRvHomeGuide;
    @BindView(R.id.etTianMaoSearch)
    EditText mEtTianMaoSearch;
    @BindView(R.id.btnTianMaoSearch)
    Button mBtnTianMaoSearch;
    /**
     * 数据源
     */
    List<List> mAllList = new ArrayList<>();

    View fragmentView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = LayoutInflater.from(getContext()).inflate(R.layout.tian_mao_fragment, null);
        ButterKnife.bind(this, fragmentView);
        requestTianmaoHome();
        return fragmentView;
    }

    /**
     * 初始化recyclerView的数据
     */
    public void initRecyclerView() {
        //将我们需要的数据传到的总数据中
        mAllList.add(requestTianmaoBanner());
        mAllList.add(mHomeBeanList);

        //运行RecylcerView这里注意，由于本类是fragment，所以我们要先使用getActivity获取一个FragmentActivity的对象，在主线程，也就是runUiThread中运行
        final FragmentActivity fragment = getActivity();
        if (fragment != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(RecyclerView.VERTICAL);
                    mRvHomeGuide.setLayoutManager(manager);
                    TianMaoAdapter adapter = new TianMaoAdapter(mAllList);
                    mRvHomeGuide.setAdapter(adapter);

                }
            });
        }
    }

    /**
     * 请求引导界面的数据
     */
    public void requestTianmaoHome() {
        String url = "https://www.tmall.com/?page_offline=true";
        OkHttpUtils.requestData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String html;
                if (response.body() != null) {
                    html = response.body().string();
                    mHomeBeanList = TianmaoData.TianMaoHome(html);
                    initRecyclerView();
                }


            }
        });
    }

    /**
     * 请求广告轮播图数据
     */
    public ArrayList<TianmaoBannerBean> requestTianmaoBanner() {
        return TianmaoData.TianMaoBannerBean();
    }

    @OnClick({R.id.btnTianMaoSearch,R.id.etTianMaoSearch})
    public void onClick(View view) {
        final int i = view.getId();
        if (i == R.id.btnTianMaoSearch || i == R.id.etTianMaoSearch) {
            Intent intent = new Intent(getActivity(), TianMaoProductInfoActivity.class);
            startActivity(intent);
        }
    }


}
