package com.example.shoppingapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.base.DensityUtil;
import com.example.shoppingapp.dialog.LongClickCancelDialog;
import com.example.shoppingapp.sql.SQLiteUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCarFragment extends DialogFragment {
    @BindView(R.id.rvShopping)
    RecyclerView mRvShopping;
    SQLiteUtils mSQLiteUtils;
    ShoppingAdapter mShopAdapter;
    ArrayList<HashMap<String, String>> mDateList = new ArrayList<>();

    public static ShoppingCarFragment newInstance() {
        ShoppingCarFragment fragment = new ShoppingCarFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.shoppingDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.shopping_car_fragment, null);
        mSQLiteUtils = new SQLiteUtils(getContext());
        ButterKnife.bind(this, view);
        initKeyList();
        initAdapter();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        //获得本 Dialog 的对象
        final Dialog dialog = getDialog();
        if (dialog != null) {
            //用于 dp 转 px 的类
            final DensityUtil densityUtil = new DensityUtil();
            //获取窗口的对象
            final Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                //弹窗宽度设为铺满屏幕
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                //高度设为 500dp
                params.height = densityUtil.dip2px(getContext(), 500);
                //位置设在底部
                params.gravity = Gravity.BOTTOM;
//               如果说 style xml文件设置们没有效果的话，可以使用这个试试
//              params.windowAnimations = R.style.shoppingDialogAnimation;
                window.setAttributes(params);
            }
        }
        initKeyList();
    }

    private void initKeyList() {
        mDateList.clear();
        List<String> list = new ArrayList<>();
        list.add("title");
        list.add("picture");
        list.add("price");
        list.add("number");
        mDateList.addAll(mSQLiteUtils.queryAllData(list, "Shopping"));
        if (mShopAdapter != null) {
            mShopAdapter.notifyDataSetChanged();
        }
    }


    private void initAdapter() {
        if (mShopAdapter == null) {
            mShopAdapter = new ShoppingAdapter(mDateList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRvShopping.setLayoutManager(layoutManager);
            mRvShopping.setAdapter(mShopAdapter);
        }

    }


    class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder> {
        ArrayList<HashMap<String, String>> mMapArrayList;


        public ShoppingAdapter(ArrayList<HashMap<String, String>> mapArrayList) {
            mMapArrayList = mapArrayList;
        }

        class ShoppingViewHolder extends RecyclerView.ViewHolder {
            /**
             * 产品图象
             */
            @BindView(R.id.ivShoppingImg)
            ImageView mIvShoppingImg;
            /**
             * 产品数量
             */
            @BindView(R.id.tvProductNumber)
            TextView mTvProductNumber;
            /**
             * 按钮加
             */
            @BindView(R.id.ivProductAdd)
            ImageView mIvProductAdd;
            /**
             * 按钮减
             */
            @BindView(R.id.ivProductReduce)
            ImageView mIvProductReduce;
            /**
             * 产品名称
             */
            @BindView(R.id.tvShoppingTitle)
            TextView mTvShoppingTitle;
            /**
             * 产品价格
             */
            @BindView(R.id.tvShoppingPrice)
            TextView mTvShoppingPrice;
            /**
             * 行品信息
             */
            @BindView(R.id.tvShoppingInfo)
            TextView mTvShoppingInfo;

            public ShoppingViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        @NonNull
        @Override
        public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_car_viewholder, null);
            return new ShoppingViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {
            HashMap<String, String> map = mMapArrayList.get(position);
            for (String key : map.keySet()) {
                if ("title".equals(key)) {
                    String value = map.get(key);
                    holder.mTvShoppingTitle.setText(value);
                    setButtonClick(holder, value);
                    //长按事件
                    holder.itemView.setOnLongClickListener(v -> {
                        LongClickCancelDialog dialog = new LongClickCancelDialog(getContext());
                        dialog.setOkClick(v1 -> {
                            mSQLiteUtils.deleteData("Shopping", "title", value);
                            mMapArrayList.remove(position);
                            mShopAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        });
                        dialog.show();
                        return false;
                    });
                } else if ("picture".equals(key)) {
                    Glide.with(holder.itemView.getContext()).load(map.get(key)).into(holder.mIvShoppingImg);
                } else if ("price".equals(key)) {
                    holder.mTvShoppingPrice.setText(map.get(key));
                } else if ("number".equals(key)) {
                    holder.mTvProductNumber.setText(map.get(key));
                }
            }
        }

        @Override
        public int getItemCount() {
            return mMapArrayList.size();
        }

        /**
         * 设置添加按钮的点击事件
         *
         * @param holder
         */
        private void setButtonClick(ShoppingViewHolder holder, String value) {
            holder.mIvProductAdd.setOnClickListener(v -> {
                setClick(holder, value, true);
            });
            holder.mIvProductReduce.setOnClickListener(v -> {
                setClick(holder, value, false);
            });

        }
    }

    private void setClick(ShoppingAdapter.ShoppingViewHolder holder, String value, boolean isAdd) {
        long number = Integer.parseInt(holder.mTvProductNumber.getText().toString());
        if (isAdd) {
            number++;
        } else {
            number--;
        }
        //如果大于99就不能再加小于0就不能再减
        if (number > 99 || number <= 0) {
            return;
        }
        holder.mTvProductNumber.setText(String.valueOf(number));
        mSQLiteUtils.upNumberDate("Shopping", number, value);
    }

}
