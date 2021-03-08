package com.example.shoppingapp.base;

import android.content.Context;

public class DensityUtil {

    public DensityUtil(){

    }
    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
