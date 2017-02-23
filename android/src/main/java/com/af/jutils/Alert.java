package com.af.jutils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;

/**
 * Created by pedja on 31.5.16. 11.01.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class Alert
{
    private static boolean SUPERTOAST_AVAILABLE;
    public static boolean USE_SUPERTOAST = true;

    static
    {
        try
        {
            Class.forName("com.github.johnpersano.supertoasts.library.SuperToast");
            SUPERTOAST_AVAILABLE = true;
        }
        catch (ClassNotFoundException e)
        {
            SUPERTOAST_AVAILABLE = false;
        }
    }
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
        if(message == null)
            return;
        if(SUPERTOAST_AVAILABLE && USE_SUPERTOAST)
        {
            if(length == Toast.LENGTH_LONG)
            {
                length = Style.DURATION_LONG;
            }
            else if(length == Toast.LENGTH_SHORT)
            {
                length = Style.DURATION_SHORT;
            }
            Style style = new Style();
            style.type = Style.TYPE_STANDARD;
            style.frame = Style.FRAME_STANDARD;
            final SuperToast toast = SuperToast.create(context, Html.fromHtml(message), length);
            toast.show();
        }
        else
        {
            if(length > 1)
            {
                length = Toast.LENGTH_LONG;
            }
            Toast.makeText(context, Html.fromHtml(message), length).show();
        }
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
        showToast(context, context.getString(resId));
    }
}
