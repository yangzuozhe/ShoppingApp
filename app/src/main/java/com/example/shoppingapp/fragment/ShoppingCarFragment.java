package com.example.shoppingapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCarFragment extends BaseFragment {
    @BindView(R.id.rvShopping)
    RecyclerView mRvShopping;

    public static ShoppingCarFragment newInstance() {
        ShoppingCarFragment fragment = new ShoppingCarFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.shopping_car_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initAdapter() {

    }


    class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder> {

        public ShoppingAdapter() {

        }

        class ShoppingViewHolder extends RecyclerView.ViewHolder {
            public ShoppingViewHolder(@NonNull View itemView) {
                super(itemView);
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

        }

        @Override
        public int getItemCount() {
            return 0;
        }


    }

}
