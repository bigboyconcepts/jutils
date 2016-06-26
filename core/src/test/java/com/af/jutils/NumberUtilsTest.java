package com.af.jutils;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by pedja on 31.5.16. 13.27.
 * This class is part of the android-utility
 * Copyright © 2016 ${OWNER}
 */
public class NumberUtilsTest
{
    @Test
    public void randomNumberInRangeTest()
    {
        int rand = NumberUtils.nextIntInRange(0, 10);
        assertTrue(rand >= 0 && rand <= 10);

        rand = NumberUtils.nextIntInRange(0, 5);
        assertTrue(rand >= 0 && rand <= 5);

        rand = NumberUtils.nextIntInRange(5, 5);
        assertTrue(rand == 5);
    }


}
