package com.transsion.example.myapplication2.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dongrp on 2016/6/22.
 * 快捷吐司工具类
 */
public class ToastUtil {

    /**
     * 快捷打印吐司的静态全局方法
     * @param context
     * @param str
     */
    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
