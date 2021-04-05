package com.example.shoppingapp.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseActivity extends AppCompatActivity {
    FragmentManager mManager;
    FragmentTransaction mTransaction;
    /**
     * 键盘管理
     */
    InputMethodManager mInputManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ARouter inject 注入
        ARouter.getInstance().inject(this);
        StatusBarUtils.setHalfTransparent(this);
    }



    /**
     * 替换碎片，这里表示替换这个碎片会让之前的碎片小时不见
     *
     * @param fragment 这里表示你要替换的碎片
     * @param parentId 这里表示你这个碎片的父布局容器，一般都是FrameLayout
     * @param isBack   表示是否添加返回栈
     */
    public void replaceFragment(Fragment fragment, int parentId, boolean isBack) {
        if (mManager == null) {
            mManager = getSupportFragmentManager();
        }
        if (mTransaction == null){
            mTransaction = mManager.beginTransaction();
        }
        mTransaction.replace(parentId, fragment);
        if (isBack) {
            //这句话让碎片有返回栈，没有这个的话，就相当于之前的碎片就被消除了，按下返回键直接退出程序了
            mTransaction.addToBackStack(null);
        }
        mTransaction.commit();
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
        if (mInputManager != null) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                mInputManager =
                        (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputManager.showSoftInput(editText, 0);
            }
        }, 200);

    }

    public void hideSoftInputFromWindow(EditText editText) {
        if (mInputManager == null) {
            mInputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        new Handler().postDelayed(() -> mInputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0),200);
    }

    /**
     * 这个方法会让旧的fragment 不会重新实例化对象，这样子写很好
     *
     * @param oldFragment 旧的碎片
     * @param newFragment 新的碎片
     * @param parentId    需要搭载的父容器
     */
    public void addFragmentWithoutClearOld(Fragment oldFragment, Fragment newFragment, int parentId) {
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        //判断旧的控件是否为空
        if (oldFragment != null) {
            //判断新的控件对象是否已经被添加了
            if (!newFragment.isAdded()) {
                transaction.hide(oldFragment).add(parentId, newFragment).commit();
            } else {
                transaction.hide(oldFragment).show(newFragment).commit();
            }
        } else {
            if (!newFragment.isAdded()) {
                transaction.add(parentId, newFragment).commit();
            }
        }
    }


}
