package com.af.jutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.af.androidutility.lib.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

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
     */
    public static float convertDpToPixel(float dp)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }


    /**
     * Converts pixels into Density-independent pixel (dp)
     *
     * @param px      input value
     */
    public static float convertPixelsToDp(float px)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return Math.round(px / (metrics.densityDpi / 160f));
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

    /**
     * Read file from /res/raw to string
     *
     * @param rawResId of the file
     * @return Content of the file as string
     */
    public static String readRawFile(Context context, int rawResId) throws IOException
    {
        InputStream is = context.getResources().openRawResource(rawResId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String read;
        StringBuilder sb = new StringBuilder();
        while ((read = br.readLine()) != null)
        {
            sb.append(read);
        }
        return sb.toString();
    }

    public static int getActionBarSize(Activity activity)
    {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
        {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static boolean isRTL()
    {
        int layoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
        return layoutDirection == ViewCompat.LAYOUT_DIRECTION_RTL;
    }

    public static int getStatusBarHeight(final Context context)
    {
        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            return resources.getDimensionPixelSize(resourceId);
        else
            return (int) Math.ceil((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 24 : 25) * resources.getDisplayMetrics().density);
    }
}
