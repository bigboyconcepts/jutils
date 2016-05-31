package com.af.jutils;

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
}
