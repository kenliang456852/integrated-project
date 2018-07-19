package com.integrated.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: DateUtils
 * Description: 对日期进行操作
 * Author: liangchao
 * Date: 2018/5/9 15:08
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class DateUtils {
    public static final String DATE_ALL_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_Time_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_DAY_PATTERN = "yyyy-MM-dd";
    public static SimpleDateFormat simTimeFormat = new SimpleDateFormat(DATE_Time_PATTERN);
    public static SimpleDateFormat simDayFormat = new SimpleDateFormat(DATE_DAY_PATTERN);

    public static String getNowTime() {
        return formatDateTime(new Date());
    }

    public static String formatDateTime(Date date) {
        if(null == date) {
            return StringUtils.EMPTY;
        }
        return simTimeFormat.format(date);
    }

    public static Date parseDateTime(String dateStr) {
        if( StringUtils.isBlank(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，
            // 比如2007-02-29会被接受，并转换成2007-03-01
            simTimeFormat.setLenient(false);
            date = simTimeFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    public static String formatDateDay(Date date) {
        if(null == date) {
            return StringUtils.EMPTY;
        }
        return simDayFormat.format(date);
    }

    public static Date parseDateDay(String dateStr) {
        if( StringUtils.isBlank(dateStr)) {
            return new Date();
        }
        Date date = null;
        try {
            date = simDayFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

}