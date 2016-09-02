package com.zhuanla;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/8/30.
 */
public class MyApplication extends Application {
    private static MyApplication app;
    private Context context;
    public static MyApplication getApp() {

        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;
    }
}
