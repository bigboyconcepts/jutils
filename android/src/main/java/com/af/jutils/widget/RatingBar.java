package com.af.jutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.af.androidutility.lib.R;
import com.af.jutils.AndroidUtils;


/**
 * Created by pedja on 20.7.16. 12.54.
 * This class is part of the Iemand
 * Copyright © 2016 ${OWNER}
 */

public class RatingBar extends LinearLayout
{
    private ImageView[] mStars;
    private int mRating;

    public RatingBar(Context context)
    {
        super(context);
    }

    public RatingBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RatingBar, defStyleAttr, defStyleRes);

        int orientation = a.getInt(R.styleable.RatingBar_android_orientation, HORIZONTAL);
        //noinspection WrongConstant
        setOrientation(orientation);

        int stars = a.getInt(R.styleable.RatingBar_stars, 5);
        boolean enabled = a.getBoolean(R.styleable.RatingBar_android_enabled, true);
        setEnabled(enabled);

        mRating = a.getInt(R.styleable.RatingBar_rating, 0);

        int icon = a.getResourceId(R.styleable.RatingBar_drawable, 0);

        int starSpacing = a.getDimensionPixelOffset(R.styleable.RatingBar_star_spacing, (int) AndroidUtils.convertDpToPixel(2.5f));

        a.recycle();

        mStars = new ImageView[stars];
        for(int i = 0; i < stars; i++)
        {
            mStars[i] = new ImageView(getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            mStars[i].setLayoutParams(params);
            if(icon > 0)
            {
                mStars[i].setImageResource(icon);
            }
            else
            {
                throw new IllegalArgumentException("Invalid drawable");
            }

            mStars[i].setSelected(i < mRating);

            //noinspection ResourceType
            mStars[i].setPadding((i == 0 ? 0 : starSpacing), 0, (i == stars - 1 ? 0 : starSpacing), 0);

            addView(mStars[i], params);

            final int finalI = i;
            mStars[i].setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(isEnabled())
                        setRating(finalI + 1);
                }
            });
        }
    }

    @Override
    public void setOrientation(int orientation)
    {
        assertOrientation(orientation);
        super.setOrientation(orientation);
    }

    public void setRating(int rating)
    {
        mRating = rating;
        if(mRating < 0)
            mRating = 0;
        else if(mRating > mStars.length)
            mRating = mStars.length;
        updateSelectedStars();
    }

    public int getRating()
    {
        return mRating;
    }

    private void updateSelectedStars()
    {
        for(int i = 0; i < mStars.length; i++)
        {
            mStars[i].setSelected(i < mRating);
        }
    }

    private void assertOrientation(int orientation)
    {
        if(orientation == VERTICAL)
            throw new IllegalArgumentException("Only LinearLayout.HORIZONTAL is supported");
    }
}