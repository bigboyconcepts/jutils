package com.af.jutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.af.androidutility.lib.R;

/**
 * Created by pedja on 10/9/13 10.17.
 * This class is part of the ${PROJECT_NAME}
 * Copyright © 2014 ${OWNER}
 *
 * @author Predrag Čokulov
 */
public class AndroidUtils
{
    private AndroidUtils() {}

    /**
     * Converts Density-independent pixel (dp) into pixels
     *
     * @param dp      input value
     * @param context Context for retrieving resources
     */
    public static float convertDpToPixel(float dp, Context context)
    {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    public static void openLink(Activity activity, String url)
    {
        if (url == null) return;
        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent = Intent.createChooser(intent, null);
        activity.startActivity(intent);
    }

    public static void setTextOrHideIfTextIsNull(@NonNull TextView textView, @NonNull LinearLayout textViewContainer, @Nullable String text)
    {
        if (StringUtils.isStringValid(text))
        {
            textView.setText(text);
        }
        else
        {
            textViewContainer.setVisibility(View.GONE);
        }
    }

    /**
     * Checks if current thread (thread that this method is called from) is applications main thread
     */
    public static boolean isMainThread()
    {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Check if application with specific package name is installed on device*/
    public static boolean isAppInstalled(Context context, String packageName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    public static int getVersionCode(Context context)
    {
        PackageInfo pInfo = null;
        try
        {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            //practically cant happen
        }
        return pInfo.versionCode;
    }

    public static String getVersionName(Context context)
    {
        PackageInfo pInfo = null;
        try
        {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            //practically cant happen
        }
        return pInfo.versionName;
    }

    public static boolean isTablet(@NonNull Context context)
    {
        return context.getResources().getBoolean(R.bool.is_tablet);
    }

    public static boolean isLandscape(@NonNull Context context)
    {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
