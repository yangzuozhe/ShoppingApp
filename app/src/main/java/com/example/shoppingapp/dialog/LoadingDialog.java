package com.example.shoppingapp.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.shoppingapp.R;

public class LoadingDialog extends Dialog {
    ImageView mIvLoading;
    ObjectAnimator mLoadingAnimator;

    public LoadingDialog(Context context) {
        super(context, R.style.loadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        mIvLoading = findViewById(R.id.ivLoading);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        initAnimator();
    }

    private void initAnimator() {
        if (mIvLoading != null) {
            mLoadingAnimator = ObjectAnimator.ofFloat(mIvLoading, View.ROTATION, 0, 360);
            mLoadingAnimator.setDuration(500);
            mLoadingAnimator.setInterpolator(new LinearInterpolator());
            mLoadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mLoadingAnimator.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mLoadingAnimator != null) {
            mLoadingAnimator.cancel();
        }
    }
}
