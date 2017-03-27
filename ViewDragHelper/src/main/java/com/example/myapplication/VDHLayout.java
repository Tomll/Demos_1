package com.example.myapplication;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dongrp on 2016/8/1.
 * ViewDragHelper的使用，并且最终去实现一个类似DrawerLayout的一个自定义的ViewGroup
 * 其中的 子view可以拖动
 */
public class VDHLayout extends LinearLayout {

    private ViewDragHelper mDrager;

    /**
     * 构造方法
     */
    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         *  创建实例需要3个参数，第一个就是当前的ViewGroup，
         *  第二个sensitivity，主要用于设置touchSlop:
         *  第三个参数就是Callback，在用户的触摸过程中会回调相关方法
         */
        mDrager = ViewDragHelper.create(this, 10.f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
        });
    }

    /**
     * onInterceptTouchEvent中通过使用mDragger.shouldInterceptTouchEvent(event)
     * 来决定我们是否应该拦截当前的事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDrager.shouldInterceptTouchEvent(ev);
    }

    /**
     * onTouchEvent中通过mDragger.processTouchEvent(event)处理事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDrager.processTouchEvent(event);
        return true;
    }
}
