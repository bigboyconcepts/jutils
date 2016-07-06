package com.af.jutils;

import java.util.Random;

/**
 * Created by pedja on 31.5.16. 11.03.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class NumberUtils
{
    private static Random RANDOM = new Random();
    private NumberUtils()
    {

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
            if (Logging.LOGGING) System.out.println(log);
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
            if (Logging.LOGGING) System.out.println(log);
        }
        return mDefault;
    }

    /**
     * Tries to parse String as Float
     * if error occurs default value will be returned
     *
     * @param value    String to parse as int
     * @param mDefault default value to return in case of value is not integer
     * @return parsed float or default value
     */
    public static double parseFloat(String value, float mDefault)
    {
        try
        {
            return Float.parseFloat(value);
        }
        catch (NumberFormatException e)
        {
            String log = "Utility.parseFloat >> failed to parse " + value + " as float";
            if (Logging.LOGGING) System.out.println(log);
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
            String log = "Utility.parseLong >> failed to parse " + value + " as integer";
            if (Logging.LOGGING) System.out.println(log);
        }
        return mDefault;
    }

    public static int nextIntInRange(int min, int max)
    {
        return min + RANDOM.nextInt((max - min) + 1);
    }
}
