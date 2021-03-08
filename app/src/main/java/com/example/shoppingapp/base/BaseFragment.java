package com.example.shoppingapp.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    InputMethodManager mInputManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * EditText获取焦点弹出软键盘
     *
     * @param editText
     */
    public void showSoftInputFromWindow(EditText editText) {
        if (mInputManager != null) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                mInputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputManager.showSoftInput(editText, 0);
            }
        }, 200);

    }

    public void hideSoftInputFromWindow(EditText editText) {
        if (mInputManager == null) {
            mInputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        new Handler().postDelayed(() -> mInputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0), 200);
    }


}
