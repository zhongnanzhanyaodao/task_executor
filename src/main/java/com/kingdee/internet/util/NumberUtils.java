package com.kingdee.internet.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

/**
 * 数字及金额工具类
 */
public class NumberUtils {
    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 根据指定字符串获取相应的BigDecimal
     * @param source
     * @param defaultValue 默认值
     * @return
     */
    public static BigDecimal getBigDecimal(String source, String defaultValue) {
        BigDecimal result = getBigDecimal(source);
        return result == null ? getBigDecimal(defaultValue) : result;
    }

    /**
     * 根据指定字符串获取相应的BigDecimal
     * @param source
     * @return
     */
    public static BigDecimal getBigDecimal(String source) {
        String preHandle = StringUtils.replaceChars(source, ",", StringUtils.EMPTY); //去除逗号
        if(StringUtils.isBlank(preHandle) || !isParsable(preHandle)) {
            return null;
        }
        return new BigDecimal(preHandle);
    }
}
