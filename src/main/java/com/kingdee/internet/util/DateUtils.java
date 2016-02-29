package com.kingdee.internet.util;

import com.kingdee.internet.security.Exceptions;

import java.text.ParseException;
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
}
