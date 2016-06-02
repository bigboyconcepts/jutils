package com.af.jutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by pedja on 31.5.16. 13.52.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class IOUtils
{
    private IOUtils()
    {

    }

    public static String readStreamToString(InputStream stream) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder string = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null)
        {
            string.append(line);
        }
        return string.toString();
    }
}
