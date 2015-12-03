package com.tianzh.cm.util.datetime;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Author: cyc
 * Date: 12-3-21
 * Time: 下午3:23
 * Description: to write something
 */
public class DateTimeUtils {
    public static int getCurrentMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    public static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    public static int getCurrentMonthOfYear(){
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }

    public static int getCurrentDayOfMonth(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static String getLastDateHour() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        return format.format(calendar.getTime());
    }

    public static String getCurrentDateHour() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        return format.format(calendar.getTime());
    }

    public static String getCurrentTime(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }

    public static String getCurrentDay() {
        return new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    }

    public static String getCurrentMonth() {
        return new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime());
    }

    public static String getOrderTime() {
        return new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
    }

    public static String getXJPayOrderRequestTime() {
        return new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
    }

    public static String getNetGamePayPayOrderRequestTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    }

    public static String getSpecifyDate(int i){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - i);
        return format.format(calendar.getTime());
    }

    public static String getSpecifyFormatDate(int i){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - i);
        return format.format(calendar.getTime());
    }

    public static void main(String[] args){

        String channelOrder = "yndlo09";

        channelOrder += DateTimeUtils.getCurrentMonthOfYear();
        if (DateTimeUtils.getCurrentDayOfMonth() > 9) {
            char day = (char) (DateTimeUtils.getCurrentDayOfMonth() + 87);
            channelOrder += day;
        } else {
            channelOrder += DateTimeUtils.getCurrentDayOfMonth();
        }
        channelOrder+="006057092005".substring("006057092005".length()-2);

        BigInteger orderId = new BigInteger("1450075");
        String orderS = Long.toOctalString(Long.parseLong(orderId.toString()));

        for (int i = 10 - orderS.length(); i > 0; i--) {
            channelOrder += "0";
        }

        channelOrder += orderS;

        System.out.println(channelOrder);
    }
}
