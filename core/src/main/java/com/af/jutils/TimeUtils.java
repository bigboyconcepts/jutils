package com.af.jutils;

import java.text.DecimalFormat;

public class TimeUtils
{
    private long timerStart = 0;
    private long timerStartTotal = 0;
    private long iterations = 0;
    private double timeElapsed;

    public TimeUtils()
    {
        timerStart = System.currentTimeMillis();
        timerStartTotal = System.currentTimeMillis();
    }

    private String getFormatted(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.000");
        return decimalFormat.format(number);
    }

    public String get()
    {
        iterations++;
        timeElapsed = 1.0 * (System.currentTimeMillis() - timerStart) / 1000; // seconds
        String result = " [time:" + getFormatted(timeElapsed) + " seconds]";
        timerStart = System.currentTimeMillis();
        return result;
    }

    String avg()
    {
        timeElapsed = 1.0 * (System.currentTimeMillis() - timerStartTotal) / 1000 / iterations; // seconds
        return " [time:" + getFormatted(timeElapsed) + " seconds]";
    }

    String total()
    {
        timeElapsed = 1.0 * (System.currentTimeMillis() - timerStartTotal) / 1000; // seconds
        return " [time:" + getFormatted(timeElapsed) + " seconds]";
    }

    public void log(String message)
    {
        if(Logging.LOGGING) System.out.println(message + get());
    }

    public void logAvg(String message)
    {
        if(Logging.LOGGING) System.out.println(message + avg());
    }

    public void logTotal(String message)
    {
        if(Logging.LOGGING) System.out.println(message + total());
    }

    public void reset()
    {
        timerStart = System.currentTimeMillis();
        timerStartTotal = System.currentTimeMillis();
        iterations = 0;
        timeElapsed = 0;
    }
}
