package transage.com.a3dgallery;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by dongrp on 2016/10/20.
 * PageTransformer是ViewPager内部的公共成员接口，我们定义一个类MyPagerTransformer实现这个接口，来具体实现我们自己的Transform逻辑
 */
public class MyPagerTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    private static final float MAX_ROTATE = 30;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {//左边的项已经滑出左边屏幕
        } else if (position < 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        } else if (position >= 0 && position < 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        } else if (position >= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        }


    }
}
