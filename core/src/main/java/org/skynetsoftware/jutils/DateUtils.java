package org.skynetsoftware.jutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by pedja on 31.5.16. 11.10.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class DateUtils
{
    private DateUtils()
    {

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
     * format date with provided DateFormat
     **/
    public static String formatDateString(DateFormat outFormat, long date)
    {
        return outFormat.format(date);
    }

    /**
     * Parse date with inFormat and re-format it with outFormat
     * @param inFormat format which will be used to parse date
     * @param outFormat format that will be used to format parsed date
     * @param date date to format
     * */
    public static String parseAndFormatDateString(SimpleDateFormat inFormat, SimpleDateFormat outFormat, String date)
    {
        return parseAndFormatDateString(inFormat, outFormat, date, null);
    }


    /**
     * Parse date with inFormat and re-format it with outFormat
     * @param inFormat format which will be used to parse date
     * @param outFormat format that will be used to format parsed date
     * @param date date to format
     * @param fallback string to return if parsing fails, default is null
     * */
    public static String parseAndFormatDateString(SimpleDateFormat inFormat, SimpleDateFormat outFormat, String date, String fallback)
    {
        try
        {
            return outFormat.format(inFormat.parse(date));
        }
        catch (ParseException e)
        {
            if (Logging.LOGGING) e.printStackTrace();
            return fallback;
        }
    }

    /**
     * Parse date with given inFormat*/
    public static long parseDateString(SimpleDateFormat inFormat, String date)
    {
        return parseDateString(inFormat, date, 0);
    }


    /**
     * Parse date with given inFormat
     * @param fallback date to return if parsing fails, default is 0*/
    public static long parseDateString(SimpleDateFormat inFormat, String date, long fallback)
    {
        if (date == null) return fallback;
        try
        {
            return inFormat.parse(date).getTime();
        }
        catch (ParseException e)
        {
            if (Logging.LOGGING)
               System.out.println("failed to parse date '" + date + "', error: " + e.getMessage());
            return fallback;
        }
    }
}
