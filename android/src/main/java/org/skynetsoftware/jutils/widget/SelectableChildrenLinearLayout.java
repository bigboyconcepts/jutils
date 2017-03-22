package org.skynetsoftware.jutils.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by pedja on 1.8.16. 11.32.
 * This class is part of the Iemand
 * Copyright © 2016 ${OWNER}
 */

public class SelectableChildrenLinearLayout extends LinearLayout
{
    private boolean initCompleted = false;

    public SelectableChildrenLinearLayout(Context context)
    {
        super(context);
    }

    public SelectableChildrenLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public SelectableChildrenLinearLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelectableChildrenLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
        if(!initCompleted)
        {
            initCompleted = true;
            for(int i = 0; i < getChildCount(); i++)
            {
                View child = getChildAt(i);
                final int finalI = i;
                child.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        selectChild(finalI);
                    }
                });
            }
        }
    }

    public void selectChild(int position)
    {
        for(int i = 0; i < getChildCount(); i++)
        {
            getChildAt(i).setSelected(i == position);
        }
    }

    public int getSelectedChildId()
    {
        View view = null;
        for(int i = 0; i < getChildCount(); i++)
        {
            View tmp = getChildAt(i);
            if(tmp.isSelected())
            {
                view = tmp;
            }
        }
        if(view != null)
        {
            return view.getId();
        }
        return 0;
    }
}
