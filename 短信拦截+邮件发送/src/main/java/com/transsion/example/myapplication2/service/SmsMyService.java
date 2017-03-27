package com.transsion.example.myapplication2.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.transsion.example.myapplication2.activity.SMSActivity;

/**
 * Created by dongrp on 2016/6/21.
 */
public class SmsMyService extends Service {
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
        Intent startIntent = new Intent(getApplicationContext(), SMSActivity.class); //接收到广播后，跳转到MainActivity
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("stringMsg", intent.getStringExtra("stringMsg"));
        getApplicationContext().startActivity(startIntent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
