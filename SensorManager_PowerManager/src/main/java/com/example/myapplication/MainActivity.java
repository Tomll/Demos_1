package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;

/**
 * 结合距离传感器 进行 电源管理（距离传感器 触发 PowerManager灭屏）
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager; //传感器管理对象
    private PowerManager powerManager;//电源管理对象
    private PowerManager.WakeLock wakeLock;//电源锁锁

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(32, "MyPower");

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        //注册传感器监听
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            wakeLock.release(); //一定要记得释放掉电源锁，否则会出现异常
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] its = event.values;
        //Log.d(TAG,"its array:"+its+"sensor type :"+event.sensor.getType()+" proximity type:"+Sensor.TYPE_PROXIMITY);
        if (its != null && event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            //经过测试，当手贴近距离感应器的时候its[0]返回值为0.0，当手离开时返回1.0
            if (its[0] == 0.0) {// 贴近手机
                if (wakeLock.isHeld()) {
                    return;
                } else {
                    wakeLock.acquire();// 申请设备电源锁（灭屏）
                }
            } else {// 远离手机
                if (wakeLock.isHeld()) {
                    return;
                } else {
                    wakeLock.setReferenceCounted(false);
                    wakeLock.release(); // 释放设备电源锁（亮屏）
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
