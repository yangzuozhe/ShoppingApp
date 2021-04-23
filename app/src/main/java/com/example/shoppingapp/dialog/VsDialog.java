package com.example.shoppingapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.shoppingapp.R;

public class VsDialog extends Dialog {
    public VsDialog(@NonNull Context context) {
        super(context, R.style.shoppingDialog);
        setContentView(R.layout.dialog_vs_view);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window !=  null){
           WindowManager.LayoutParams params = window.getAttributes();
           params.gravity = Gravity.BOTTOM;
           params.height = WindowManager.LayoutParams.WRAP_CONTENT;
           params.width = WindowManager.LayoutParams.MATCH_PARENT;
        }
    }
}
