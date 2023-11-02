package com.springtime.fun.util;

import com.springtime.fun.exception.AppsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarUtil {

    public static final String DEFAULT_DATE_TIME_FORMAT_SECONDS = "dd/MM/yyyy HH:mm:ss";

    public static String getDefaultFormattedDateTimeMaskNull(Date date) {
        if (date != null) {
            return getFormattedDate(date, DEFAULT_DATE_TIME_FORMAT_SECONDS);
        }
        return null;
    }

    public static String getFormattedDate(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static Date getDefaultParsedDateTimeOnly(String date) throws AppsException {
        return getParsedDate(date, DEFAULT_DATE_TIME_FORMAT_SECONDS);
    }

    public static Date getParsedDate(String date, String dateFormat) throws AppsException {
        Date parsedDate;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            throw new AppsException("Invalid date format");
        }
        return parsedDate;
    }
}
