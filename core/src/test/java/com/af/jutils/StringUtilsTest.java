package com.af.jutils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pedja on 31.5.16. 13.27.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class StringUtilsTest
{
    @Test
    public void randomIsStringValidTest()
    {
        assertEquals(org.skynetsoftware.jutils.StringUtils.isStringValid("null"), false);
        assertEquals(org.skynetsoftware.jutils.StringUtils.isStringValid(""), false);
        assertEquals(org.skynetsoftware.jutils.StringUtils.isStringValid(" "), true);
        assertEquals(org.skynetsoftware.jutils.StringUtils.isStringValid(" ", true), false);
        assertEquals(org.skynetsoftware.jutils.StringUtils.isStringValid(null), false);
    }


}
