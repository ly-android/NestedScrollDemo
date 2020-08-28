package com.allen.android.nestedscrolldemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by liyong on 2020/8/28.
 */
public class NestedScrollLinearLayout extends LinearLayout implements NestedScrollingParent {
    public static final String TAG = "NestedScroll";
    private static final int OFFSET = 200;
    private NestedScrollingParentHelper mNestedScrollingParentHelper;

    public NestedScrollLinearLayout(Context context) {
        super(context);
    }

    public NestedScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //向下
        if (dy < 0) {
            if (getTranslationY() >= 0) {
                consumed[0] = 0;
                consumed[1] = (int) Math.max(getTranslationY() - OFFSET, dy);
                setTranslationY(getTranslationY() - consumed[1]);
            }
        } else {
            if (getTranslationY() <= OFFSET) {
                consumed[0] = 0;
                consumed[1] = (int) Math.min(dy, getTranslationY());
                setTranslationY(getTranslationY() - consumed[1]);
            }
        }
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.d(TAG, "onNestedScrollAccepted: axes=" + axes);
        getNestedScrollingParentHelper().onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        getNestedScrollingParentHelper().onStopNestedScroll(child);
    }


    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, String.format("onNestedScroll: dyConsumed:%d,dyUnconsumed:%d", dyConsumed, dyUnconsumed));
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    private NestedScrollingParentHelper getNestedScrollingParentHelper() {
        if (mNestedScrollingParentHelper == null) {
            mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }
        return mNestedScrollingParentHelper;
    }
}
