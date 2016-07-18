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
        assertEquals(StringUtils.isStringValid("null"), false);
        assertEquals(StringUtils.isStringValid(""), false);
        assertEquals(StringUtils.isStringValid(" "), true);
        assertEquals(StringUtils.isStringValid(" ", true), false);
        assertEquals(StringUtils.isStringValid(null), false);
    }


}
