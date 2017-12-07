package me.leduyhung.mdaily.helper;

import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hungleduy on 12/1/17.
 */

public class CalendarUtil {

    private Calendar calendar;
    private static CalendarUtil calendarUtil;

    public static CalendarUtil newInstance() {

        if (calendarUtil == null)
            calendarUtil = new CalendarUtil();
        return calendarUtil;
    }

    public CalendarUtil() {

        calendar = Calendar.getInstance();
    }

    public int getCurrentYear() {

        return calendar.get(Calendar.YEAR);
    }

    public String convertDateToString(Date date) {

        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }
}