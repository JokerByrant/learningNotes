package com.sxh.ts.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sxh
 * @date 2021/11/12
 */
public class DateUtil {
    public static String getTodayStr() {
        return DateFormatByStr(new Date());
    }

    /**
     * 返回指定格式的日期字符串
     *
     * @param date
     * @return
     */
    public static String DateFormatByStr(Date date) {
        return DateFormatByStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回指定格式的日期字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String DateFormatByStr(Date date, String formatStr) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
}
