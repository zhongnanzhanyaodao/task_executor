package com.kingdee.internet.util;

import com.kingdee.internet.security.Exceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类处理类
 */
public class DateUtils {
    private DateUtils() {
        throw new UnsupportedOperationException();
    }

    public static Date parseDate(String source, String... parsePatterns) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(source, parsePatterns);
        } catch (ParseException e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String now2str(DateFormat dateFormat) {
        return date2str(new Date(), dateFormat);
    }

    public static String date2str(Date date, DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static String getYearDiffFromNow(int amount, DateFormat dateFormat) {
        return getYearDiffFromDate(new Date(), amount, dateFormat);
    }

    public static String getYearDiffFromDate(Date date, int amount, DateFormat dateFormat) {
        return DateFormat.YYYYMMDD.format(org.apache.commons.lang3.time.DateUtils.addYears(date, amount));
    }

    public enum DateFormat {
        YYYYMMDD("yyyyMMdd"), YYYY_MM_DD("yyyy-MM-dd");

        private final SimpleDateFormat SDF;
        DateFormat(String pattern) {
            SDF = new SimpleDateFormat(pattern);
        }

        public String pattern() {
            return SDF.toPattern();
        }

        public String format(Date date) {
            return SDF.format(date);
        }
    }
}
