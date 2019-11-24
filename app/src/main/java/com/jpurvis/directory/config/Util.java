package com.jpurvis.directory.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static Date timezoneDateFromString(String date) {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        Calendar cal = Calendar.getInstance(tz);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setCalendar(cal);
        try {
            cal.setTime(sdf.parse("2013-07-17T03:58:00.000Z"));
            return cal.getTime();
        } catch (ParseException e) {
            return null;
        }
    }
}
