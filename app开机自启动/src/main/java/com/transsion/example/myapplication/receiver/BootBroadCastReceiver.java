package com.transsion.example.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.transsion.example.myapplication.service.BootMyService;

/**
 * Created by dongrp on 2016/6/21.
 * 自定义广播接收器：用于接收开机广播
 */
public class BootBroadCastReceiver extends BroadcastReceiver {

    static String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_BOOT)) {
//            Intent StartIntent = new Intent(context, MainActivity.class); //接收到广播后，跳转到MainActivity
//            StartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(StartIntent);

            Intent StartIntent = new Intent(context, BootMyService.class); //接收到广播后，开启MyService （通过MyService跳转到MainActivity）
            StartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(StartIntent);

        }

    }
}
