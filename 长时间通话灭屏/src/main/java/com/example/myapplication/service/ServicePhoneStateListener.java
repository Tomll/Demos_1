package com.example.myapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by dongrp on 2016/8/4.
 */
public class ServicePhoneStateListener extends Service {
    private boolean isInCall = false;
    private  String TAG = "ServicePhoneStateListener";
    private PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
    private PowerManager.WakeLock wakeLock = pm.newWakeLock(32,"MyWakeLock");
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            screenJudge(); //20s后判断通话状态及屏幕亮灭状态，做出相应操作
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerPhoneStateOfInCall();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        wakeLock.release();
        startService( new Intent(this,ServicePhoneStateListener.class));
    }

    /**
     * 判断屏幕是否亮着，亮着：灭掉
     */
    private  void screenJudge(){
        if (pm.isScreenOn()&&isInCall==true){
            wakeLock.acquire();
        }
    }

    /**
     * PhoneState状态监听
     */
    private void registerPhoneStateOfInCall() {
        Log.i(TAG, "registerPhoneStateOfInCall");
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING://来电振动
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:// 接通电话
                        //在这里进行接通电话的处理,不过如果是拨打电话的时候,不会有来电振动,会直接到这个状态
                        isInCall = true;
                        handler.sendEmptyMessageDelayed(0,10*1000);
                        break;
                    case TelephonyManager.CALL_STATE_IDLE://挂断电话
                        isInCall = false;
                        break;
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }


}
