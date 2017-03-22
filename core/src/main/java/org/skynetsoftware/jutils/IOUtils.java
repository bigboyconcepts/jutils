package org.skynetsoftware.jutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

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

    public static void copyStream(InputStream is, OutputStream os) throws IOException
    {
        byte[] buffer = new byte[4 * 1024]; // or other buffer size
        int read;

        while ((read = is.read(buffer)) != -1)
        {
            os.write(buffer, 0, read);
        }
        os.flush();
    }
}
