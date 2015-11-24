package com.af.androidutility.lib;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pedja on 10/9/13 10.17.
 * This class is part of the ${PROJECT_NAME}
 * Copyright © 2014 ${OWNER}
 *
 * @author Predrag Čokulov
 */
public class AndroidUtility
{
    private static final String LOG_TAG = "android-utility";
    private AndroidUtility() {}

    public static final Pattern MAIL_PATTERN = Pattern.compile("(.+)@.+\\.[a-z]+");

    /**
     * General Purpose AlertDialog
     */
    public static AlertDialog showMessageAlertDialog(Context context, String message,
                                                     String title, DialogInterface.OnClickListener listener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title != null ? Html.fromHtml(title) : null);
        builder.setMessage(message != null ? Html.fromHtml(message) : null);
        builder.setPositiveButton(android.R.string.ok, listener);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * General Purpose AlertDialog
     */
    public static AlertDialog showMessageAlertDialog(Context context, int message, int title, DialogInterface.OnClickListener listener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title > 0) builder.setTitle(Html.fromHtml(context.getString(title)));
        builder.setMessage(Html.fromHtml(context.getString(message)));
        builder.setPositiveButton(android.R.string.ok, listener);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * General Purpose Toast
     */
    public static void showToast(Context context, String message, int length)
    {
        Toast.makeText(context, message != null ? Html.fromHtml(message) : null, length).show();
    }

    /**
     * General Purpose Toast
     */
    public static void showToast(Context context, String message)
    {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    /**
     * General Purpose Toast
     */
    public static void showToast(Context context, int resId)
    {
        Toast.makeText(context, Html.fromHtml(context.getString(resId)), Toast.LENGTH_LONG).show();
    }

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

    /**
     * Check if email is in valid format(eg. test@gmail.com)
     *
     * @param email email to validate
     */
    public static boolean isEmailValid(CharSequence email)
    {
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();
    }

    /**
     * Tries to parse String as Integer
     * if error occurs default value will be returned
     *
     * @param value    String to parse as int
     * @param mDefault default value to return in case of value is not integer
     * @return parsed int or default value
     */
    public static int parseInt(String value, int mDefault)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            String log = "Utility.parseInt >> failed to parse " + value + " as integer";
            if (Logging.LOGGING) Log.w(LOG_TAG, log);
        }
        return mDefault;
    }

    /**
     * Tries to parse String as Integer
     * if error occurs default value will be returned
     *
     * @param value    String to parse as int
     * @param mDefault default value to return in case of value is not integer
     * @return parsed int or default value
     */
    public static double parseDouble(String value, double mDefault)
    {
        try
        {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException e)
        {
            String log = "Utility.parseDouble >> failed to parse " + value + " as double";
            if (Logging.LOGGING) Log.w(LOG_TAG, log);
        }
        return mDefault;
    }

    /**
     * Tries to parse String as Long
     * if error occurs default value will be returned
     *
     * @param value    String to parse as long
     * @param mDefault default value to return in case of value is not long
     * @return parsed long or default value
     */
    public static long parseLong(String value, long mDefault)
    {
        try
        {
            return Long.parseLong(value);
        }
        catch (NumberFormatException e)
        {
            String log = "Utility.parseInt >> failed to parse " + value + " as integer";
            if (Logging.LOGGING) Log.w(LOG_TAG, log);
        }
        return mDefault;
    }

    /**
     * Generate md5 sum from string
     */
    public static String md5(final String s)
    {
        final String MD5 = "MD5";
        try
        {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest)
            {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
            String log = "Utility.md5 >> error: " + e.getMessage();
            if (Logging.LOGGING) Log.e(LOG_TAG, log);
        }
        return "";
    }

    /**
     * Encode string as URL UTF-8
     */
    @SuppressWarnings("deprecation")
    public static String encodeString(String string)
    {
        return URLEncoder.encode(string);
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

    public static String decodeString(String input)
    {
        try
        {
            return URLDecoder.decode(input, "UTF-8");
        }
        catch (Exception e)
        {
            if(Logging.LOGGING)e.printStackTrace();
            return input;
        }
    }


    public static String toUpperCase(String text)
    {
        return text == null ? null : text.toUpperCase();
    }

    public static String sanitizeUrl(String url)
    {
        if (url == null) return null;
        url = url.replaceAll("\\\\", "/");
        url = url.replaceAll("(?<!http:)//", "/");
        url = Uri.encode(url, "@#&=*+-_.,:!?()/~'%");
        return url;
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

    public static boolean isStringValid(String value)
    {
        return !TextUtils.isEmpty(value) && !"null".equals(value.toLowerCase());
    }

    public static String formatDateString(@NotNull SimpleDateFormat outFormat, long date)
    {
        return outFormat.format(date);
    }

    public static String parseAndFormatDateString(@NotNull SimpleDateFormat inFormat, @NotNull SimpleDateFormat outFormat, String date)
    {
        try
        {
            return outFormat.format(inFormat.parse(date));
        }
        catch (ParseException e)
        {
            if (Logging.LOGGING) e.printStackTrace();
            return null;
        }
    }

    public static long parseDateString(@NotNull SimpleDateFormat inFormat, String date)
    {
        if(date == null)return 0;
        try
        {
            return inFormat.parse(date).getTime();
        }
        catch (ParseException e)
        {
            if (Logging.LOGGING) Log.w(LOG_TAG, "failed to parse date '" + date + "', error: " + e.getMessage());
            return 0;
        }
    }

    public static void setTextOrHideIfTextIsNull(@NotNull TextView textView, @NotNull LinearLayout textViewContainer, @Nullable String text)
    {
        if (isStringValid(text))
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
     * Calculate larges possible power of two image size smaller than maxWidth*/
    public static int getMaxImageSizePOW(int maxWidth)
    {
        int res = 2;
        while (res < maxWidth)
        {
            res *= 2;
        }
        return res == maxWidth ? res : res / 2;
    }

    /**
     * Calculate number of days between to dates*/
    public static int daysBetween(Calendar cal1, Calendar cal2)
    {
        int daysBetween = 0;
        while (cal1.before(cal2))
        {
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
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
}
