package com.conant.ums.util;

import java.text.*;
import java.util.*;


public class MyCalendar {
    /**
     * calendar constructor comment.
     */
    public MyCalendar() {
        super();
    }

    static final int not_leap_date[] =
        new int[] {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final int leap_date[] = new int[] {
        31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static String sep[] = new String[2];

    //取当前系统日期，返回西元：YYYYMMDD
    public String getCurDay() {
        return new SysTime().getSysDay();
    }

    //计算输入日期（YYYYMMDD)为该年第几天
    public static int getYearN(String sDay) {
        int iYearN = 0;

        String syear = sDay.substring(0, 4);
        String smonth = sDay.substring(4, 6);
        String sday = sDay.substring(6, 8);
        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();
        int iday = Integer.valueOf(sday).intValue();

        for (int i = 1; i < imonth; i++) {
            iYearN += getMonthE(iyear, i);
        }

        iYearN += iday;

        return iYearN;
    }

    //取输入日期（YYYYMMDD）为星期几（星期日为0）
    public static int getWeekN(String sDay) {
        int iWeekN = 0;
        int iYearN = 0;

        String syear = sDay.substring(0, 4);
        String smonth = sDay.substring(4, 6);
        String sday = sDay.substring(6, 8);
        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();
        int iday = Integer.valueOf(sday).intValue();

        iYearN = getYearN(sDay);
        iWeekN = convert_to_week(syear, iYearN);

        return iWeekN;
    }

    //取输入日期（YYYYMMDD）的那个星期的所有日期（从星期日到星期六）
    public static String[] getWeekDays(String sDay) {
        String[] sWeekDays = new String[7];

        int iWeekN = getWeekN(sDay);
        sWeekDays[iWeekN] = sDay;
        for (int i = iWeekN; i > 0; i--) {
            sWeekDays[i - 1] = getPreDay(sWeekDays[i]);
            //System.out.print("the week's pre day is" + sWeekDays[i - 1] + "\n");
        }

        for (int i = iWeekN; i < 6; i++) {
            sWeekDays[i + 1] = getNextDay(sWeekDays[i]);
            //System.out.print("the week's next day is" + sWeekDays[i + 1] + "\n");
        }
        //sWeekDays[0] = "20030619";

        return sWeekDays;
    }

    //取输入日期（YYYYMMDD）该月结束为几号
    public static int getMonthE(String sDay) {
        int iWeekN = 0;

        String syear = sDay.substring(0, 4);
        String smonth = sDay.substring(4, 6);
        String sday = sDay.substring(6, 8);
        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();
        int iday = Integer.valueOf(sday).intValue();

        if (leap_year(syear) == true) {
            iWeekN = leap_date[imonth - 1];
        }
        else {
            iWeekN = not_leap_date[imonth - 1];
        }
        return iWeekN;
    }

    //取输入日期（YYYY，MM）该月结束为几号
    public static int getMonthE(String syear, String smonth) {
        int iWeekN = 0;

        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();

        if (leap_year(syear) == true) {
            iWeekN = leap_date[imonth - 1];
        }
        else {
            iWeekN = not_leap_date[imonth - 1];
        }
        return iWeekN;
    }

    //取输入日期（YYYY，MM）该月结束为几号
    public static int getMonthE(int iyear, int imonth) {
        int iWeekN = 0;

        if (leap_year(iyear) == true) {
            iWeekN = leap_date[imonth - 1];
        }
        else {
            iWeekN = not_leap_date[imonth - 1];
        }
        return iWeekN;
    }

    //取输入日期（YYYYMMDD）的次日（YYYYMMDD）
    public static String getNextDay(String sDay) {
        String sNextDay = null;

        String syear = sDay.substring(0, 4);
        String smonth = sDay.substring(4, 6);
        String sday = sDay.substring(6, 8);
        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();
        int iday = Integer.valueOf(sday).intValue();

        if (iday >= getMonthE(sDay)) {
            if (++imonth > 12) {
                imonth = 1;
                ++iyear;
            }

            iday = 1;
        }
        else {
            ++iday;
        }

        syear = String.valueOf(iyear);
        if (imonth < 10) {
            smonth = "0" + String.valueOf(imonth);
        }
        else {
            smonth = String.valueOf(imonth);
        }
        if (iday < 10) {
            sday = "0" + String.valueOf(iday);
        }
        else {
            sday = String.valueOf(iday);
        }

        sNextDay = syear + smonth + sday;

        return sNextDay;
    }

    //取输入日期（YYYYMMDD）的前一日（YYYYMMDD）
    public static String getPreDay(String sDay) {
        String sPreDay = null;

        String syear = sDay.substring(0, 4);
        String smonth = sDay.substring(4, 6);
        String sday = sDay.substring(6, 8);
        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();
        int iday = Integer.valueOf(sday).intValue();

        if (iday <= 1) {
            //iday = 1;
            if (--imonth < 1) {
                imonth = 12;
                --iyear;
                iday = 31;
            }
            else {
                if (leap_year(iyear)) {
                    iday = leap_date[imonth - 1];
                }
                else {
                    iday = not_leap_date[imonth - 1];
                }
            }

        }
        else {
            --iday;
        }

        syear = String.valueOf(iyear);
        if (imonth < 10) {
            smonth = "0" + String.valueOf(imonth);
        }
        else {
            smonth = String.valueOf(imonth);
        }
        if (iday < 10) {
            sday = "0" + String.valueOf(iday);
        }
        else {
            sday = String.valueOf(iday);
        }

        sPreDay = syear + smonth + sday;

        return sPreDay;
    }

    //取输入日期（YYYYMMDD）的第N日（YYYYMMDD）
    public static String getAfterDay(String sDay, int n) {
        String afterDay = new String();

        String syear = sDay.substring(0, 4);
        String smonth = sDay.substring(4, 6);
        String sday = sDay.substring(6, 8);
        int iyear = Integer.valueOf(syear).intValue();
        int imonth = Integer.valueOf(smonth).intValue();
        int iday = Integer.valueOf(sday).intValue();

        String sTmpDay = syear + "." + smonth + "." + sday;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = sdf.parse(sTmpDay, new ParsePosition(0));
        Calendar calendar = sdf.getCalendar();
        //calendar.get(Calendar.WEEK_OF_YEAR);
        int iByn = calendar.get(Calendar.DAY_OF_YEAR);
        int iAft = iByn + n;
        int iydays = 365;

        if (leap_year(syear)) {
            iydays = 366;
        }

        if (iAft > iydays) {
            iyear++;
            iAft = iAft - iydays;
        }

        String sTmpDate = convert_to_date(String.valueOf(iyear), iAft);
        afterDay = String.valueOf(iyear) + sTmpDate;

        return afterDay;
    }

    public static void main(String[] args) {
        String str = new String();
        try {
//			int by = convert_to_byte("2000", "03/12");
//			String date = convert_to_date("2001", 61);
//			int week = convert_to_week("2003", 10);
//
//			int weekdays[] = new int[] { 0, 0, 0, 0, 0, 1, 1 };
//			String styear = year_string("2002", weekdays);
//
//                        System.out.println(getMonthE("20030622"));
//                        System.out.println(getNextDay("20031231"));
//
//			//System.out.println(roll("2000-6-30",65));
//			//System.out.println(by);
//			//System.out.println(date);
//			System.out.println(week);
//                        System.out.println(getWeekN("20030622"));
//                        /*String[] ss = getWeekDays("20030619");
//                        for(int i=0;i<7;i++){
//                          System.out.println(ss[i]);
//                        }*/
//                        getWeekDays("20030619");
//			//System.out.println(leap_year("2000"));
//			//System.out.println(styear);
            System.out.print("the preday is " + getNextDay("20030630"));
            getWeekDays("20030624");
            System.out.print("the weekday is" + getWeekN("20030721"));
            str = getAfterDay("20030901", 10);
            System.out.print("the weekday is" + str);

        }
        catch (Exception e) {
        }
        ;
    }

    public static int convert_to_byte(String year, String date) {
        int d = 0;

        String syear = year;
        String sdate = date;

        int index = sdate.indexOf("/");
        int lastindex = sdate.length();

        //System.out.println("the index number is :"+index);
        //System.out.println("the last index number is :"+lastindex);

        String smonth = sdate.substring(0, index);
        String sday = sdate.substring(index + 1, lastindex);

        Integer inmonth = Integer.valueOf(smonth);
        Integer inday = Integer.valueOf(sday);

        //System.out.println(inmonth+":"+inday);

        int imonth = inmonth.intValue();
        int iday = inday.intValue();

        if (leap_year(syear) == true) {
            for (int i = 0; i < imonth - 1; i++) {
                d = d + leap_date[i];
            }
            d = d + iday;
            return d;
        }
        else {
            for (int i = 0; i < imonth - 1; i++) {
                d = d + not_leap_date[i];
            }
            d = d + iday;
            return d;
        }
    }

    public static int convert_to_byte(String date) {
        int d = 0;

        String sdate = date;

        //int index = sdate.indexOf("/");
        //int lastindex =sdate.length();

        //System.out.println("the index number is :"+index);
        //System.out.println("the last index number is :"+lastindex);

        String syear = sdate.substring(0, 4);
        String smonth = sdate.substring(5, 7);
        String sday = sdate.substring(8, 10);

        Integer inmonth = Integer.valueOf(smonth);
        Integer inday = Integer.valueOf(sday);

        //System.out.println(syear+":"+inmonth+":"+inday);

        int imonth = inmonth.intValue();
        int iday = inday.intValue();

        if (leap_year(syear) == true) {
            for (int i = 0; i < imonth - 1; i++) {
                d = d + leap_date[i];
            }
            d = d + iday;
            return d;
        }
        else {
            for (int i = 0; i < imonth - 1; i++) {
                d = d + not_leap_date[i];
            }
            d = d + iday;
            return d;
        }
    }

    public static String convert_to_date(String year, int n) {
        String syear = year;
        String sday;
        String smonth;

        String sdate = null;

        int imonth = 0;
        int iday = 1;

        if (leap_year(syear) == true) {
            for (int i = 0; i < 12; i++) {
                if (n > 0) {
                    imonth++;
                    iday = n;
                }
                else {
                    break;
                }

                n = n - leap_date[i];
            }
        }
        else {
            for (int i = 0; i < 12; i++) {
                if (n > 0) {
                    imonth++;
                    iday = n;
                }
                else {
                    break;
                }

                n = n - not_leap_date[i];
            }
        }

        smonth = String.valueOf(imonth);
        if (smonth.length() < 2) {
            smonth = "0" + smonth;
        }
        sday = String.valueOf(iday);
        if (sday.length() < 2) {
            sday = "0" + sday;
        }
        //sdate = smonth + '/' + sday;
        sdate = smonth + sday;
        return sdate;
    }

    public static int convert_to_week(String year, int n) {
        String syear = year;
        Integer inyear = Integer.valueOf(syear);
        int iyear = inyear.intValue();

        int date = 0;
        int week = 0;
        int comp = 0;

        int minus = iyear - 2000;
        if (minus > 0) {
            comp = minus - 1;
        }
        if (minus <= 0) {
            comp = -minus;
        }
        //System.out.println(minus);

        if (minus > 0) {
            date = 365 * minus + comp / 4 + n;

            //System.out.println(" zhen temp date is:"+date);
            date = date % 7;
            switch (date) {
                case 0:
                    week = 6;
                    break;
                case 1:

                    //week = 7;
                    week = 0;
                    break;
                case 2:
                    week = 1;
                    break;
                case 3:
                    week = 2;
                    break;
                case 4:
                    week = 3;
                    break;
                case 5:
                    week = 4;
                    break;
                case 6:
                    week = 5;
                    break;
            }
        }
        if (minus == 0) {
            date = 365 * minus + comp / 4 + n - 1;

            //System.out.println(" zhen temp date is:"+date);
            date = date % 7;
            switch (date) {
                case 0:
                    week = 6;
                    break;
                case 1:
                    week = 7;
                    break;
                case 2:
                    week = 1;
                    break;
                case 3:
                    week = 2;
                    break;
                case 4:
                    week = 3;
                    break;
                case 5:
                    week = 4;
                    break;
                case 6:
                    week = 5;
                    break;
            }
        }

        if (minus < 0) {
            date = 365 * ( -minus) + comp / 4 - n + 1;

            //System.out.println(" minus temp date is:"+date);

            date = date % 7;
            switch (date) {
                case 0:
                    week = 6;
                    break;
                case 1:
                    week = 5;
                    break;
                case 2:
                    week = 4;
                    break;
                case 3:
                    week = 3;
                    break;
                case 4:
                    week = 2;
                    break;
                case 5:
                    week = 1;
                    break;
                case 6:
                    week = 7;
                    break;
            }

        }
        date = date % 7;

        return week;

        //return String.valueOf(week);
    }

    public static String year_string(String year, int[] weekday) {
        String y_string = "";
        String syear = year;
        String sweek = "";
        int iweek;
        int iweekday[] = weekday;

        //System.out.println(weekday[0]);

        iweek = convert_to_week(syear, 1);
        //Integer inweek = Integer.valueOf(sweek);
        //iweek = inweek.intValue();

        if (leap_year(syear) == true) {
            for (int i = 0; i < 366; i++) {
                y_string += String.valueOf(iweekday[iweek - 1]);
                iweek++;
                if (iweek == 8) {
                    iweek = 1;
                }
            }
        }
        else {
            for (int i = 0; i < 365; i++) {
                y_string = y_string + String.valueOf(iweekday[iweek - 1]);
                iweek++;
                if (iweek == 8) {
                    iweek = 1;
                }
            }
            y_string = y_string + " ";
        }

        return y_string;
    }

    public static String year_string(
        String year,
        int w1,
        int w2,
        int w3,
        int w4,
        int w5,
        int w6,
        int w7) {
        int[] week = new int[7];
        week[0] = w1;
        week[1] = w2;
        week[2] = w3;
        week[3] = w4;
        week[4] = w5;
        week[5] = w6;
        week[6] = w7;
        return year_string(year, week);
    }

    static boolean leap_year(String year) {
        String syear = year;
        Integer inyear = Integer.valueOf(syear);
        int iyear = inyear.intValue();

        return leap_year(iyear);

    }

    static boolean leap_year(int iyear) {
        if (iyear % 400 == 0) {
            return true;
        }
        else
        if (iyear % 4 == 0 && iyear % 100 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void separate(String str, String sign) {
        str = str.trim();
        int flag = str.indexOf(sign);
        sep[0] = str.substring(0, flag);
        sep[1] = str.substring(flag + 1, str.length() - 1);
    }

    public String getString(int i) {
        return sep[i];
    }

    //没有包括行业日历信息的日期滚动
    //str的格式为：yyyy/mm/dd 或者是yyyy-mm-dd
    //df : 正数向前滚动，负数向后滚动
    //例如：2000/03/01或者2001/03/01向后滚动一天：2000/02/29，20001/2/28
    //          roll("2000/03/01",-1);
    public static String roll(String str, int df) {
        boolean slash = str.indexOf("/") > 0;
        StringTokenizer st = null;
        if (slash) {
            st = new StringTokenizer(str, "/");
        }
        else {
            st = new StringTokenizer(str, "-");
        }
        int year = 0;
        int month = 0;
        int day = 0;
        year = new Integer(st.nextToken()).intValue();
        month = new Integer(st.nextToken()).intValue() - 1;
        day = new Integer(st.nextToken()).intValue();
        GregorianCalendar worldTour = new GregorianCalendar(year, month, day);
        worldTour.add(GregorianCalendar.DATE, df);
        String nyear = String.valueOf(worldTour.get(GregorianCalendar.YEAR));
        String nmonth = String.valueOf(worldTour.get(GregorianCalendar.MONTH) +
                                       1);
        String nday = String.valueOf(worldTour.get(GregorianCalendar.DATE));
        if (nday.length() < 2) {
            nday = "0" + nday;
        }
        if (nmonth.length() < 2) {
            nmonth = "0" + nmonth;
        }
        if (slash) {
            return nyear + "/" + nmonth + "/" + nday;
        }
        return nyear + "-" + nmonth + "-" + nday;
    }

}
