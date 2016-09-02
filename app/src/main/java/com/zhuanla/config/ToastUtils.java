package com.zhuanla.config;


import android.widget.Toast;

import com.zhuanla.MyApplication;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ToastUtils {

    public static void shortToast(String str){
        Toast.makeText(MyApplication.getApp(),str,Toast.LENGTH_SHORT).show();
    }
    public static void longToast(String str){
        Toast.makeText(MyApplication.getApp(),str,Toast.LENGTH_LONG).show();
    }
}
