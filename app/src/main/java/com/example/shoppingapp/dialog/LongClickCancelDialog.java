package com.example.shoppingapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shoppingapp.R;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 长按弹窗
 *
 * @author HB.yangzuozhe
 * @date 2021-03-03
 */
public class LongClickCancelDialog extends Dialog {
    @BindView(R.id.btnOk)
    Button mBtnOk;
    @BindView(R.id.btnCancel)
    Button mBtnCancel;

    public LongClickCancelDialog(@NonNull Context context) {
        super(context);
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.long_click_cancel_dialog, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        mBtnCancel.setOnClickListener(v -> {
            dismiss();
        });
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 确定按钮的点击事件的回调
     *
     * @param listener
     */
    public void setOkClick(View.OnClickListener listener) {
        mBtnOk.setOnClickListener(listener);

    }

}
