package org.skynetsoftware.jutils.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by pedja on 14.2.16..
 */
public class UntouchableViewPager extends ViewPager
{
    private boolean swipeEnabled = false;

    public UntouchableViewPager(Context context)
    {
        super(context);
    }

    public UntouchableViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return swipeEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        return swipeEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean executeKeyEvent(KeyEvent event)
    {
        return swipeEnabled && super.executeKeyEvent(event);
    }

    public void setSwipeEnabled(boolean swipeEnabled)
    {
        this.swipeEnabled = swipeEnabled;
    }
}