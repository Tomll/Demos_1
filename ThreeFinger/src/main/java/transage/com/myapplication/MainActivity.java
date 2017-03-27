package transage.com.aidl_client;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    float y1 = 0;
    float y2 = 0;
    float y3 = 0;
    boolean isY = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointerCount = event.getPointerCount();
        // int allPoint;

        Log.v("dongrp", "pointerCount=" + pointerCount);
        Log.v("dongrp", "actionIdex=" + event.getActionIndex());

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.v("dongrp", "ACTION_DOWN");
            case MotionEvent.ACTION_POINTER_UP:
                Log.v("dongrp", "pointerCount1=" + pointerCount);
            case MotionEvent.ACTION_POINTER_DOWN:
                //Log.v("zhaoxin_ontouch", "ACTION_POINTER_DOWN" + "");
                if (pointerCount == 1) {
                    y1 = event.getY(0);
                    Log.v("dongrp", "y1=" + y1);
                    // y3= event.getY(2);
                } else if (pointerCount == 2) {
                    y2 = event.getY(1);
                    Log.v("dongrp", "y2=" + y2);
                } else if (pointerCount == 3) {
                    y3 = event.getY(2);
                    Log.v("dongrp", "y3=" + y3);
                    //  gestureDetector.onTouchEvent(event);
                }
            case MotionEvent.ACTION_UP:
                if (pointerCount == 1 && isY) {
                    isY = false;
                    Log.v("dongrp", "screenshoot");
                    PackageManager packageManager = getPackageManager();
                    Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.transage.privatespace");
                    startActivity(launchIntentForPackage);
                }

                return super.onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                //判断是否是三指下滑动作
                if (pointerCount == 3 && ((event.getY(0) - y1) > 300 && (event.getY(1) - y2) > 300 && (event.getY(2) - y3) > 300)) {
                    isY = true;
                    Log.v("dongrp", "y1_=" + (event.getY(0) - y1));
                    Log.v("dongrp", "y2_=" + (event.getY(1) - y2));
                    Log.v("dongrp", "y3_=" + (event.getY(2) - y3));
                    Log.v("dongrp", "ACTION_MOVE");
                }
        }
        //zhaoxin  add  end


        return super.onTouchEvent(event);

    }
}
