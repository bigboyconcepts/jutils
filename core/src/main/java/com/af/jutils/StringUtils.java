package com.af.jutils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by pedja on 31.5.16. 11.04.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class StringUtils
{
    private StringUtils()
    {

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
            String log = "StringUtils.md5 >> error: " + e.getMessage();
            if (Logging.LOGGING) System.out.println(log);
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

    public static String decodeString(String input)
    {
        try
        {
            return URLDecoder.decode(input, "UTF-8");
        }
        catch (Exception e)
        {
            if (Logging.LOGGING) e.printStackTrace();
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
        url = encode(url, "@#&=*+-_.,:!?()/~'%");
        return url;
    }

    public static String encode(String s, String allow)
    {
        if (s == null)
        {
            return null;
        }

        // Lazily-initialized buffers.
        StringBuilder encoded = null;

        int oldLength = s.length();

        // This loop alternates between copying over allowed characters and
        // encoding in chunks. This results in fewer method calls and
        // allocations than encoding one character at a time.
        int current = 0;
        while (current < oldLength)
        {
            // Start in "copying" mode where we copy over allowed chars.

            // Find the next character which needs to be encoded.
            int nextToEncode = current;
            while (nextToEncode < oldLength
                    && isAllowed(s.charAt(nextToEncode), allow))
            {
                nextToEncode++;
            }

            // If there's nothing more to encode...
            if (nextToEncode == oldLength)
            {
                if (current == 0)
                {
                    // We didn't need to encode anything!
                    return s;
                }
                else
                {
                    // Presumably, we've already done some encoding.
                    encoded.append(s, current, oldLength);
                    return encoded.toString();
                }
            }

            if (encoded == null)
            {
                encoded = new StringBuilder();
            }

            if (nextToEncode > current)
            {
                // Append allowed characters leading up to this point.
                encoded.append(s, current, nextToEncode);
            }
            else
            {
                // assert nextToEncode == current
            }

            // Switch to "encoding" mode.

            // Find the next allowed character.
            current = nextToEncode;
            int nextAllowed = current + 1;
            while (nextAllowed < oldLength
                    && !isAllowed(s.charAt(nextAllowed), allow))
            {
                nextAllowed++;
            }

            // Convert the substring to bytes and encode the bytes as
            // '%'-escaped octets.
            String toEncode = s.substring(current, nextAllowed);
            try
            {
                byte[] bytes = toEncode.getBytes("UTF-8");
                int bytesLength = bytes.length;
                for (int i = 0; i < bytesLength; i++)
                {
                    encoded.append('%');
                    encoded.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4]);
                    encoded.append(HEX_DIGITS[bytes[i] & 0xf]);
                }
            }
            catch (UnsupportedEncodingException e)
            {
                throw new AssertionError(e);
            }

            current = nextAllowed;
        }

        // Encoded could still be null at this point if s is empty.
        return encoded == null ? s : encoded.toString();
    }

    /**
     * Index of a component which was not found.
     */
    private final static int NOT_FOUND = -1;
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private static boolean isAllowed(char c, String allow)
    {
        return (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z')
                || (c >= '0' && c <= '9')
                || "_-!.~'()*".indexOf(c) != NOT_FOUND
                || (allow != null && allow.indexOf(c) != NOT_FOUND);
    }

    public static boolean isStringValid(String value)
    {
        return !isEmpty(value) && !"null".equals(value.toLowerCase());
    }

    public static String formatDateString(SimpleDateFormat outFormat, long date)
    {
        return outFormat.format(date);
    }

    public static String parseAndFormatDateString(SimpleDateFormat inFormat, SimpleDateFormat outFormat, String date)
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

    public static long parseDateString(SimpleDateFormat inFormat, String date)
    {
        if(date == null)return 0;
        try
        {
            return inFormat.parse(date).getTime();
        }
        catch (ParseException e)
        {
            if (Logging.LOGGING) System.out.println("failed to parse date '" + date + "', error: " + e.getMessage());
            return 0;
        }
    }

    public static boolean isEmpty(CharSequence str)
    {
        return str == null || str.length() == 0;
    }

    public static String toLowerCase(String text)
    {
        return text == null ? null : text.toUpperCase();
    }
}
