package org.skynetsoftware.jutils.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ScalingImageView extends ImageView
{

    public ScalingImageView(Context context)
    {
        super(context);
    }

    public ScalingImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ScalingImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Drawable mDrawable = getDrawable();
        if (mDrawable != null)
        {
            int mDrawableWidth = mDrawable.getIntrinsicWidth();
            int mDrawableHeight = mDrawable.getIntrinsicHeight();
            float actualAspect = (float) mDrawableWidth / (float) mDrawableHeight;

            // Assuming the width is ok, so we calculate the height.
            final int actualWidth = MeasureSpec.getSize(widthMeasureSpec);
            final int height = (int) (actualWidth / actualAspect);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}