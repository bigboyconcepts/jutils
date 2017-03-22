package com.af.jutils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pedja on 31.5.16. 13.27.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class JUtilsTest
{
    @Test
    public void isEmailValidTest()
    {
        assertEquals(true, org.skynetsoftware.jutils.JUtils.isEmailValid("predragcokulov@gmail.com"));
        assertEquals(false, org.skynetsoftware.jutils.JUtils.isEmailValid("predragcokulov"));
        assertEquals(false, org.skynetsoftware.jutils.JUtils.isEmailValid("predragc-okulov@.com"));
    }

    @Test
    public void getMaxImageSizePOWTest()
    {
        assertEquals(1024, org.skynetsoftware.jutils.JUtils.getMaxImageSizePOW(1345));
        assertEquals(1024, org.skynetsoftware.jutils.JUtils.getMaxImageSizePOW(1024));
        assertEquals(2048, org.skynetsoftware.jutils.JUtils.getMaxImageSizePOW(2049));
        assertEquals(1024, org.skynetsoftware.jutils.JUtils.getMaxImageSizePOW(2047));
        assertEquals(0, org.skynetsoftware.jutils.JUtils.getMaxImageSizePOW(0));
    }
}
