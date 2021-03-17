package com.example.shoppingapp.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //下面两行必须写在init之前，否则配置init的过程会无效
        ARouter.openLog();//打印日志
        //开启调试模式（如果在InstantRun模式下运行，必须开启调试模式）
        //线上版本需要关闭，否则会有安全风险
        ARouter.openDebug();
        //官方建议在Application中初始化使用
        ARouter.init(BaseApplication.this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //使用方式和eventBus类似，也需要在生命周期的尽头释放一下
        ARouter.getInstance().destroy();
    }
}
