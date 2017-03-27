package com.transsion.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.transsion.example.myapplication.activity.MainActivity;

import org.jetbrains.annotations.Nullable;

/**
 * Created by dongrp on 2016/6/21.
 * 自定义的后台服务：在监听到开机广播之后，开启该服务
 */
public class BootMyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent StartIntent = new Intent(getApplicationContext(), MainActivity.class); //接收到广播后，跳转到MainActivity
        StartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(StartIntent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
