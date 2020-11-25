package com.example.shoppingapp.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
    }

    /**
     * 替换碎片，这里表示替换这个碎片会让之前的碎片小时不见
     *  @param fragment 这里表示你要替换的碎片
     * @param parentId 这里表示你这个碎片的父布局容器，一般都是FrameLayout
     */
    public void replaceFragment(Fragment fragment, int parentId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(parentId, fragment);
        //这句话让碎片有返回栈，没有这个的话，就相当于之前的碎片就被消除了，按下返回键直接退出程序了
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * 添加碎片，这里表示之前的碎片我们也可以看得到
     *
     * @param fragment 这里表示你要添加的碎片
     * @param parentId 这里表示你这个碎片的父布局容器，一般都是FrameLayout
     */
    public void addPlaceFragment(Fragment fragment, int parentId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(parentId, fragment);
        //这句话让碎片有返回栈，没有这个的话，就相当于之前的碎片就被消除了，按下返回键直接退出程序了
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
