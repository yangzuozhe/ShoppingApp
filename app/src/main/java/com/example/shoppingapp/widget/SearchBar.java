package com.example.shoppingapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.shoppingapp.R;

public class SearchBar extends LinearLayout {
    GestureDetector mGestureDetector;
    MyGestureLister mGestureLister;
    SearchBarClickLister mLister;
    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.search_bar_view, null);
        addView(view);
        mGestureLister = new MyGestureLister();
        mGestureDetector = new GestureDetector(getContext(), mGestureLister);

    }

    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * 设置点击事件
     * @param lister 控件的点击事件的方法，用于外部自定义
     */
    public void setOnclick(SearchBarClickLister lister) {

        mLister = lister;
    }

    /**
     * 如果触摸事件下有控件点击事件，则重写下面方法
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(mGestureDetector.onTouchEvent(ev)){
            return mGestureDetector.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 本 控件的点击事件
     */
    public interface SearchBarClickLister {
        void onClick();
    }

    private class MyGestureLister implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            mLister.onClick();
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

}
