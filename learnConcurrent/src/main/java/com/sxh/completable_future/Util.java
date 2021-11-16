package com.sxh.completable_future;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author sxh
 * @date 2021/11/15
 */
public class Util {
    /**
     * 睡眠
     * @param millis
     */
    protected static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印时间和线程信息
     */
    protected static void printTimeAndThread(){
        printTimeAndThread("");
    }

    /**
     * 打印时间和线程信息
     * @param tag
     */
    protected static void printTimeAndThread(String tag){
        String result = new StringJoiner("\t|\t")
                .add(getTodayStr())
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(tag)
                .toString();
        System.out.println(result);
    }

    public static String getTodayStr() {
        return DateFormatByStr(new Date());
    }

    /**
     * 返回指定格式的日期字符串
     *
     * @param date
     * @return
     */
    protected static String DateFormatByStr(Date date) {
        return DateFormatByStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回指定格式的日期字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    protected static String DateFormatByStr(Date date, String formatStr) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
}
