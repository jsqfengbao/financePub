package com.finance.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author：jinshuangqi
 * @Date: Created in 2018/12/7 14:58
 * @Modified By
 **/

public class DateUtil {
    private static final String _dateFormat = "yyyy-MM-dd";

    public static Date getNowTime() {
        return new Date();
    }

    public static String date2Str(Date date, String format) {
        if ((format == null) || ("".equals(format))) {
            format = "yyyy-MM-dd";
        }
        if (date == null) {
            date = getNowTime();
        }
        DateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.format(date);
    }

    public static Date str2Date(String dateStr, String formatStr) {
        if ((formatStr == null) || ("".equals(formatStr))) {
            formatStr = "yyyy-MM-dd";
        }
        DateFormat dateformat = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dateformat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("!!!!!!!!input \"" + dateStr + "\" is worry! ");
        }
        return date;
    }


    public static double daysBetween(Date startdate, Date enddate)
            throws ParseException {
        long time2 = enddate.getTime();
        long time1 = startdate.getTime();
        long diff = time2 - time1;
        return Double.parseDouble(diff / 86400000L + "." + diff % 86400000L / 864000L);
    }

    public static double daysBetween(String startdate, String enddate, String dateFormat) throws ParseException {
        return daysBetween(str2Date(startdate, dateFormat), str2Date(enddate, dateFormat));
    }

    public static String nextDayDate(String loopDate, int addOsubNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(str2Date(loopDate, null));
        calendar.add(6, addOsubNum);
        return date2Str(calendar.getTime(), null);
    }

    public static Date converDate(String inValue) throws Exception {
        if (inValue == null) {
            return null;
        }
        String value = inValue.replaceAll("：", ":");
        if ((value != null) && (value.length() == 28) && (value.indexOf("CST") == 20)) {
            String year = value.substring(24, 28);
            String date = value.substring(8, 10);
            String temp = value.substring(4, 7);
            if (temp.equals("Jan")) {
                value = year + "-" + "01" + "-" + date;
            }
            if (temp.equals("Feb")) {
                value = year + "-" + "02" + "-" + date;
            }
            if (temp.equals("Mar")) {
                value = year + "-" + "03" + "-" + date;
            }
            if (temp.equals("Apr")) {
                value = year + "-" + "04" + "-" + date;
            }
            if (temp.equals("May")) {
                value = year + "-" + "05" + "-" + date;
            }
            if (temp.equals("Jun")) {
                value = year + "-" + "06" + "-" + date;
            }
            if (temp.equals("Jul")) {
                value = year + "-" + "07" + "-" + date;
            }
            if (temp.equals("Aug")) {
                value = year + "-" + "08" + "-" + date;
            }
            if (temp.equals("Sep")) {
                value = year + "-" + "09" + "-" + date;
            }
            if (temp.equals("Oct")) {
                value = year + "-" + "10" + "-" + date;
            }
            if (temp.equals("Nov")) {
                value = year + "-" + "11" + "-" + date;
            }
            if (temp.equals("Dec")) {
                value = year + "-" + "12" + "-" + date;
            }
        }
        if ((value.indexOf("年") > 0) && (value.indexOf("月") > 0) && (value.indexOf("日") > 0) && (value.indexOf("时") > 0) && (value.indexOf("分") > 0) && (value.indexOf("秒") > 0) && (value.indexOf("毫秒") > 0)) {
            return str2Date(value, "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
        }
        if ((value.indexOf("年") > 0) && (value.indexOf("月") > 0) && (value.indexOf("日") > 0) && (value.indexOf("时") > 0) && (value.indexOf("分") > 0) && (value.indexOf("秒") > 0)) {
            return str2Date(value, "yyyy年MM月dd日 HH时mm分ss秒");
        }
        if ((value.indexOf("/") > 0) && (value.trim().length() == 10)) {
            return str2Date(value, "yyyy/MM/dd");
        }
        if ((value.indexOf("-") > 0) && (value.trim().length() == 19)) {
            return str2Date(value, "yyyy-MM-dd HH:mm:ss");
        }
        if ((value.indexOf("-") > 0) && (value.trim().length() == 23)) {
            return str2Date(value, "yyyy-MM-dd HH:mm:ss.SSS");
        }
        if ((value.indexOf("-") > 0) && (value.trim().length() == 10)) {
            return str2Date(value, "yyyy-MM-dd");
        }
        if ((value.indexOf(":") > 0) && (value.trim().length() == 8)) {
            return str2Date(value, "HH:mm:ss");
        }
        if ((value.indexOf(":") > 0) && (value.trim().length() == 12)) {
            return str2Date(value, "HH:mm:ss.SSS");
        }
        if (value.trim().length() == 14) {
            return str2Date(value, "yyyyMMddHHmmss");
        }
        if (value.trim().length() == 17) {
            return str2Date(value, "yyyyMMddHHmmssSSS");
        }
        if ((value.indexOf(" ") > 0) && (value.trim().length() == 15)) {
            return str2Date(value, "yyyyMMdd HHmmss");
        }
        if ((value.indexOf(" ") > 0) && (value.trim().length() == 18)) {
            return str2Date(value, "yyyyMMdd HHmmssSSS");
        }
        if (value.trim().length() == 8) {
            return str2Date(value, "yyyyMMdd");
        }
        if (value.trim().length() == 6) {
            return str2Date(value, "HHmmss");
        }
        if (value.trim().length() == 9) {
            return str2Date(value, "HHmmssSSS");
        }
        return null;
    }


    public static long compareTime(String sStartdates, String bEnddates, String dateFormat)
            throws ParseException {
        Date sStartdate = str2Date(sStartdates, dateFormat);
        Date bEnddate = str2Date(bEnddates, dateFormat);
        if ((dateFormat == null) || ("".equals(dateFormat))) {
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sStartdate = sdf.parse(sdf.format(sStartdate));
        bEnddate = sdf.parse(sdf.format(bEnddate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(sStartdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bEnddate);
        long time2 = cal.getTimeInMillis();
        Long between_days = Long.valueOf((time2 - time1) / 1000L);
        return between_days.longValue();
    }
}
