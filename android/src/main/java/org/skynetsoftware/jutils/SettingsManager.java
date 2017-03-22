package org.skynetsoftware.jutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;


/**
 * Created by pedja on 2/12/14.
 * Handles all reads and writes to SharedPreferences
 *
 * @author Predrag Čokulov
 */
public class SettingsManager
{
    private static SharedPreferences PREFS;

    public static void init(@NonNull Context context)
    {
        PREFS = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSetting(String key, Object fallback)
    {
        if(PREFS == null)
        {
            throw new IllegalStateException("SettingsManager is not initialized, call 'init(Context)' first");
        }
        if(key == null)
            return null;
        if(fallback instanceof Boolean)
        {
            return (T)(Boolean)PREFS.getBoolean(key, (Boolean) fallback);
        }
        else if(fallback instanceof String)
        {
            return (T)PREFS.getString(key, (String) fallback);
        }
        else if(fallback instanceof Long)
        {
            return (T)(Long)PREFS.getLong(key, (long) fallback);
        }
        else if(fallback instanceof Integer)
        {
            return (T)(Integer)PREFS.getInt(key, (int) fallback);
        }
        else if(fallback instanceof Float)
        {
            return (T)(Float)PREFS.getFloat(key, (float) fallback);
        }
        return (T) fallback;
    }

    @SuppressWarnings("ConstantConditions")
    public static void setSetting(String key, Object value)
    {
        if(PREFS == null)
        {
            throw new IllegalStateException("SettingsManager is not initialized, call 'init(Context)' first");
        }
        SharedPreferences.Editor editor = PREFS.edit();
        if(value instanceof Boolean)
        {
            editor.putBoolean(key, (boolean) value);
        }
        else if(value instanceof String)
        {
            editor.putString(key, (String) value);
        }
        else if(value instanceof Long)
        {
            editor.putLong(key, (long) value);
        }
        else if(value instanceof  Float)
        {
            editor.putFloat(key, (float) value);
        }
        else if(value instanceof Integer)
        {
            editor.putInt(key, (int) value);
        }
        editor.apply();
    }

    public static void clearAllPrefs()
    {
        if(PREFS == null)
        {
            throw new IllegalStateException("SettingsManager is not initialized, call 'init(Context)' first");
        }
        SharedPreferences.Editor editor = PREFS.edit();
        editor.clear();
        editor.apply();
    }
}
