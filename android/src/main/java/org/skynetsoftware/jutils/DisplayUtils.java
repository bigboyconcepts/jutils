package org.skynetsoftware.jutils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;


/**
 * Created by pedja on 3/19/14 10.12.
 * This class is part of the ${PROJECT_NAME}
 * Copyright © 2014 ${OWNER}
 * @author Predrag Čokulov
 */
@SuppressWarnings("deprecation")
public class DisplayUtils
{
    public final int screenWidth;
    public final int screenHeight;

    public DisplayUtils(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("Context cannot be null");
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;
        }
        else
        {
            screenWidth = display.getWidth();
            screenHeight = display.getHeight();
        }
    }
}
