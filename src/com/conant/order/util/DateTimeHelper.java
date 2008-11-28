package com.conant.order.util;

import java.util.Date;

import java.text.SimpleDateFormat;

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

public class DateTimeHelper {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 日期时间类型转换为形如“2004-01-01 02:10:00”的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized String getDateStr(Date date) {
		if (date != null) {
			return dateFormat.format(date);
		} else
			return "";
	}

	/**
	 * 日期时间类型转换为形如“2004-01-01"的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized String getOnlyDateStr(Date date) {
		if (date != null) {
			return DATE_FORMAT.format(date);
		} else
			return "";
	}

	/**
	 * 日期时间类型转换为形如“20040101021000”的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized String getContinueDateStr(Date date) {
		if (date == null)
			return null;
		StringBuffer buffer = new StringBuffer(dateFormat.format(date));
		buffer.deleteCharAt(4);
		buffer.deleteCharAt(6);
		buffer.deleteCharAt(8);
		buffer.deleteCharAt(10);
		buffer.deleteCharAt(12);
		return buffer.toString();
	}

	/**
	 * 从字符串到日期类型
	 * 
	 * @param dateStr
	 *            "2005-07-13 12:00:00"
	 * @return
	 */
	public static synchronized Date getDate(String dateStr) {
		try {
			return dateFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把YYYY:MM:DD:HH:mm:SS 格式的时间类型转化为YYYY-MM-DD HH:mm:SS格式
	 * 
	 * @param time
	 *            String
	 * @return String
	 */
	public static String getTradTimeStr(String time) {
		int len = time.length();
		String str = time.substring(0, 4) + "-" + time.substring(5, 7) + "-"
				+ time.substring(8, 10) + " " + time.substring(11, len);
		return str;
	}

	/**
	 * 从字符串到日期类型
	 * 
	 * @param dateStr
	 *            "2005-07-13"
	 * @return
	 */
	public static synchronized Date getOnlyDate(String dateStr) {
		try {
			return DATE_FORMAT.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTransDateToDB(Date getDate) {
		if (getDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String date = df.format(getDate);
			return date;
		} else
			return "";
	}

	public static String getTransDateToTime(Date getDate) {
		if (getDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			String date = df.format(getDate);
			return date;
		}
		return "";
	}

	// test main
	public static void main(String[] args) {
		/*
		 * System.out.println(""+getTransDateToDB(new Date()));
		 * System.out.println
		 * (""+getTransDateToDB(Calendar.getInstance().getTime()));
		 * System.out.println(""+getTransDateToTime(new Date()));
		 * System.out.println(""+getContinueDateStr(new Date()));
		 * System.out.println(getContinueDateStr(new Date()).length());
		 */
		System.out.println(getOnlyDate("2005-07-13"));
		System.out.println(getOnlyDateStr(new Date()));
	}
}
