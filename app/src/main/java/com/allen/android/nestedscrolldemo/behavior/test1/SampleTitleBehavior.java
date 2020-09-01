package com.allen.android.nestedscrolldemo.behavior.test1;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {
    public static final String TAG = "SampleTitleBehavior";
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;
    private float maxDy = 0;

    public SampleTitleBehavior() {
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    //被观察的view发生改变时回调
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, String.format("onDependentViewChanged: y=%.2f", dependency.getY()));
        if (deltaY == 0) {
            deltaY = child.getHeight();
            maxDy = dependency.getY();
        }
        float actor = dependency.getY() / maxDy;
        child.setTranslationY(-deltaY * actor);
        child.setAlpha(1 - actor);
        return true;
    }
}
