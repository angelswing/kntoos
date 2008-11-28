package com.conant.order.util;

import java.text.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * <p>Title: Online-Order System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author Martin
 * @version 1.0
 */

public abstract class DTime {
    /**
     * ʱ���ʽyyyyMMddHHmmssSSS
     */
    private static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
    /**
     * ȡ��ָ��ʱ��������ֵ
     */
    public static String getActualMaximum(int field) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int max = calendar.getActualMaximum(field);
        return (max < 10 ? "0" + max : String.valueOf(max));
    }

    /**
     * ȡ��ָ���·ݵ����һ��
     */
    public static int getLastDayOfMonth(int year, int month) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        return cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    }

    /**
     * ȡ��UID
     */
    public static String getUID() {
        java.rmi.server.UID uId = new java.rmi.server.UID();
        return uId.toString().replace(':', '.');
    }

    /**
     * ȡ��ָ����ʽ��ʱ�䴮
     */
    public static String getDateTimeString(String newpt) throws Exception {
        StringBuffer dtBuf = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat(newpt);
        sdf.setLenient(false);
        sdf.format(Calendar.getInstance().getTime(), dtBuf, new FieldPosition(0));
        sdf = null;
        return dtBuf.toString();
        //return getDateTimeString(getDateTimeStamp(), "yyyyMMddHHmmssSSS", newpattern);
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static String getDateTimeString(java.util.Date date, String newpt) throws
            Exception {
        StringBuffer dtBuf = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat(newpt);
        sdf.setLenient(false);
        sdf.format(date, dtBuf, new FieldPosition(0));
        sdf = null;
        return dtBuf.toString();
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static String getDateTimeString(long midate, String newpt) throws
            Exception {
        StringBuffer dtBuf = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat(newpt);
        sdf.setLenient(false);
        sdf.format(new java.util.Date(midate), dtBuf, new FieldPosition(0));
        sdf = null;
        return dtBuf.toString();
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static String getDateTimeString(String dateStr, String newpt) throws
            Exception {
        return getDateTimeString(dateStr, TIMESTAMP_FORMAT, newpt);
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static java.util.Date getDateFromString(String dateStr) throws
            Exception {
        return getDateFromString(dateStr, TIMESTAMP_FORMAT);
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static Timestamp getTimestampFromString(String dateStr) throws
            Exception {
        java.util.Date dt = getDateFromString(dateStr, TIMESTAMP_FORMAT);
        return new Timestamp(dt.getTime());
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static java.util.Date getDateFromString(String dateStr, String pt) throws
            Exception {
        int minLen = (dateStr.length() < pt.length() ? dateStr.length() :
                      pt.length());
        SimpleDateFormat sdf = new SimpleDateFormat(pt.substring(0, minLen));
        sdf.setLenient(false);
        return sdf.parse(dateStr.substring(0, minLen));
    }

    /**
     * ת��ָ����ʽ��ʱ�䴮
     */
    public static String getDateTimeString(String dateStr, String oldpt,
                                           String newpt) throws Exception {
        if (dateStr == null || dateStr.trim().length() == 0) {
            return dateStr;
        }
        StringBuffer dtBuf = new StringBuffer();
        java.util.Date date = getDateFromString(dateStr, oldpt);
        SimpleDateFormat sdf = new SimpleDateFormat(newpt);
        sdf.setLenient(false);
        sdf.format(date, dtBuf, new FieldPosition(0));
        sdf = null;
        return dtBuf.toString();
    }

    /**
     * ȡ�õ�ǰ��ʱ�䴮
     */
    public static synchronized String getDateTimeStamp() throws Exception {
        //deprecation method
        Calendar cal = Calendar.getInstance();
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH) + 1;
        int date = cal.get(java.util.Calendar.DATE);
        int hour = cal.get(java.util.Calendar.HOUR);
        if (cal.get(java.util.Calendar.AM_PM) == java.util.Calendar.PM) {
            hour += 12;
        }
        int minute = cal.get(java.util.Calendar.MINUTE);
        int second = cal.get(java.util.Calendar.SECOND);
        int msecond = cal.get(java.util.Calendar.MILLISECOND);
        return year + (month >= 10 ? "" : "0") + month + (date >= 10 ? "" : "0") +
                date + (hour >= 10 ? "" : "0")
                + hour + (minute >= 10 ? "" : "0") + minute +
                (second >= 10 ? "" : "0") + second
                + (msecond >= 10 ? (msecond >= 100 ? "" : "0") : "00") +
                msecond;
    }

    /**
     * ��ȡ�������ڶ�����������
     */
    /*
      public static long getDatesGap(java.util.Date dstart,java.util.Date dend) throws Exception{
       java.util.Calendar calstart = java.util.Calendar.getInstance();
       java.util.Calendar calend = java.util.Calendar.getInstance();
       java.util.Calendar calmid = java.util.Calendar.getInstance();
       int ystart = dstart.getYear() + 1900;
       int yend = dend.getYear() + 1900;
       calstart.set(ystart, dstart.getMonth(), dstart.getDate());
       calend.set(yend, dend.getMonth(), dend.getDate());
       long days = 0;
       for(int i = ystart; i < yend; i++){
        calmid.set(i, 11, 31);
        days += calmid.get(Calendar.DAY_OF_YEAR);
       }
       return (days + calend.get(Calendar.DAY_OF_YEAR) - calstart.get(Calendar.DAY_OF_YEAR));
      }
     */
    /**
     * ��ȡ���������ַ�������������
     */
    public static long getDateGapFromString(String dstr) throws Exception {
        java.util.Date dstart = getDateFromString(dstr, TIMESTAMP_FORMAT);
        String now = getDateTimeStamp();
        java.util.Date dend = getDateFromString(now, TIMESTAMP_FORMAT);
        return getMillSecGap(dstart, dend);
    }

    /**
     * ��ȡ�������ڶ������������
     */
    public static long getMillSecGap(java.util.Date dstart, java.util.Date dend) {
        return (dend.getTime() - dstart.getTime());
    }

    /**
     * ȡ�õ�ǰʱ��ĺ�����
     */
    public static int getMillSec() {
        return Calendar.getInstance().get(java.util.Calendar.MILLISECOND);
    }

    /**
     * ȡ��ָ�����ڵ���һ��
     *
     * @param date
     *            ָ�����ڡ�
     * @return ָ�����ڵ���һ��
     */
    public static java.util.Date getPreDay(java.util.Date date) {
        /**
         * ��ϸ��ƣ� 1.ָ�����ڼ�1��
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, -1);
        return gc.getTime();
    }

    /**
     * ȡ��ָ�����ڵ���һ��
     *
     * @param date
     *            ָ�����ڡ�
     * @return ָ�����ڵ���һ��
     */
    public static java.util.Date getNextWeek(java.util.Date date) {
        /**
         * ��ϸ��ƣ� 1.ָ�����ڼ�1��
         */
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.DATE, 7);
        return gc.getTime();
    }

}
