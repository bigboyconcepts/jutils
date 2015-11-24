package com.af.androidutility.lib;

import android.os.Build;

/**
 * Created by pedja on 12.2.15. 08.40.
 * This class is part of the Android Utility
 * Copyright © 2015 ${OWNER}
 */
public final class Android
{
    private Android()
    {

    }

    /**
     * Check if system is minimum Android 5.0 Lollipop (SDK 21)
     * @return true if system os is 21 or higher, false otherwise*/
    public static boolean hasLollipop()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Check if system is minimum Android 4.4 Lollipop (SDK 19)
     * @return true if system os is 19 or higher, false otherwise*/
    public static boolean hasKitKat()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * Check if system is minimum Android 4.1 Lollipop (SDK 16)
     * @return true if system os is 16 or higher, false otherwise*/
    public static boolean hasJellyBean()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * Check if system is minimum Android 4.2 Lollipop (SDK 17)
     * @return true if system os is 17 or higher, false otherwise*/
    public static boolean hasJellyBeanMR1()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * Check if system is minimum Android 4.3 Lollipop (SDK 18)
     * @return true if system os is 18 or higher, false otherwise*/
    public static boolean hasJellyBeanMR2()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    /**
     * Check if system is minimum Android 4.0 Lollipop (SDK 14)
     * @return true if system os is 14 or higher, false otherwise*/
    public static boolean hasIcs()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * Check if system is minimum Android 4.0.3 Lollipop (SDK 15)
     * @return true if system os is 15 or higher, false otherwise*/
    public static boolean hasIcsMR1()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }

    /**
     * Check if system is minimum Android 2.3 Lollipop (SDK 9)
     * @return true if system os is 10 or higher, false otherwise*/
    public static boolean hasGb()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * Check if system is minimum Android 2.3.3 Lollipop (SDK 10)
     * @return true if system os is 10 or higher, false otherwise*/
    public static boolean hasGbMR1()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1;
    }
}
