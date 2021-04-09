package com.example.shoppingapp.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.shoppingapp.R;

public class GuideDialog extends Dialog {
    private ImageView mIvFingerLeft;
    private ImageView mIvCancel;
    private ObjectAnimator mAnimator;
    Context mContext;

    public GuideDialog(@NonNull Context context) {
        super(context, R.style.loadingDialog);
        mContext = context;
        setContentView(R.layout.guide_dialog);
        mIvFingerLeft = findViewById(R.id.ivFingerLeft);
        mIvCancel = findViewById(R.id.ivCancel);
        mAnimator = ObjectAnimator.ofFloat(mIvFingerLeft, View.TRANSLATION_X, 0, -100);
        mAnimator.setDuration(1500);
        mAnimator.setRepeatCount(2);
        mAnimator.start();
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dismiss();
            }
        });
        mIvCancel.setOnClickListener(v -> {
            mIvCancel.setSelected(!mIvCancel.isSelected());
        });
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mIvCancel.isSelected()) {
            if (mContext instanceof Activity) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("guide_dialog", Context.MODE_PRIVATE).edit();
                editor.putBoolean("isShowGuide", mIvCancel.isSelected());
                editor.apply();
            }

        }
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }
}
