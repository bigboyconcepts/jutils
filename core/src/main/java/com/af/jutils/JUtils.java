package com.af.jutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pedja on 31.5.16. 11.02.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class JUtils
{
    private JUtils()
    {

    }

    public static final Pattern MAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

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
     * Calculate larges possible power of two image size smaller than maxWidth*/
    public static int getMaxImageSizePOW(int maxWidth)
    {
        if(maxWidth == 0)
            return 0;
        int res = 2;
        while (res < maxWidth)
        {
            res *= 2;
        }
        return res == maxWidth ? res : res / 2;
    }

}
