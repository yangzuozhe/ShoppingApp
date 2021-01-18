package com.example.shoppingapp.base;

import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {



    /**
     * 替换碎片，这里表示替换这个碎片会让之前的碎片小时不见
     *
     * @param fragment 这里表示你要替换的碎片
     * @param parentId 这里表示你这个碎片的父布局容器，一般都是FrameLayout
     * @param isBack   表示是否添加返回栈
     */
    public void replaceFragment(Fragment fragment, int parentId, boolean isBack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(parentId, fragment);
        if (isBack) {
            //这句话让碎片有返回栈，没有这个的话，就相当于之前的碎片就被消除了，按下返回键直接退出程序了
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    /**
     * 添加碎片，这里表示之前的碎片我们也可以看得到
     *
     * @param fragment 这里表示你要添加的碎片
     * @param parentId 这里表示你这个碎片的父布局容器，一般都是FrameLayout
     * @param isBack   表示是否添加返回栈
     */
    public void addPlaceFragment(Fragment fragment, int parentId, boolean isBack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(parentId, fragment);
        if (isBack) {
            //这句话让碎片有返回栈，没有这个的话，就相当于之前的碎片就被消除了，按下返回键直接退出程序了
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    /**
     * EditText获取焦点弹出软键盘
     *
     * @param editText
     */
    public void showSoftInputFromWindow(EditText editText) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                InputMethodManager inputManager =
                        (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        },200);

    }

}
