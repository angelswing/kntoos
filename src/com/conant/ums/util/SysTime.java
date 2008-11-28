package com.conant.ums.util;

import java.util.Calendar;

/** <p> 说明：获取当前时间的字符串。 </p>*/
public class SysTime {
    private String sysDate;
    private String sysTime;
    private String sysDateSlash;
    private String sysTimeSemicolon;
    private String sysDateCN;
    private String sysTimeCN;
    private String sysDay;

    public SysTime() {
        Calendar cal = Calendar.getInstance();
        int yy = cal.get(Calendar.YEAR);
        String years = String.valueOf(yy);
        int mm = cal.get(Calendar.MONTH) + 1;
        String months = this.fillZero(mm);
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        String days = this.fillZero(dd);
        int hh = cal.get(Calendar.HOUR_OF_DAY);
        String hours = this.fillZero(hh);
        int ms = cal.get(Calendar.MINUTE);
        String minutes = this.fillZero(ms);
        int ss = cal.get(Calendar.SECOND);
        String seconds = this.fillZero(ss);
        sysDate = years + "-" + months + "-" + days;
        sysTime = hours + ":" + minutes + ":" + seconds;
        sysDateSlash = years + "/" + months + "/" + days;
        sysTimeSemicolon = sysDate + " " + hours + ":" + minutes + ":" +
            seconds;
        sysDateCN = years + "年" + months + "月" + days + "日";
        sysTimeCN = sysDateCN + " " + hours + "时" + minutes + "分" + seconds +
            "秒";
    }

    /** 获取时间格式为：YYYYMMDD的时间字符串。 */
    public String getSysDay() {
        return sysDay;
    }

    /** 获取时间格式为：HH:MS:SS的时间字符串。 */
    public String getSysTime() {
        return sysTime;
    }

    /** 获取时间格式为：YYYY-MM-DD的时间字符串。 */
    public String getSysDate() {
        return sysDate;
    }

    /** 获取时间格式为：YYYY/MM/DD的时间字符串。 */
    public String getSysDateSlash() {
        return sysDateSlash;
    }

    /** 获取时间格式为：YYYY-MM-DD HH:MS:SS的时间字符串。 */
    public String getSysTimeSemicolon() {
        return sysTimeSemicolon;
    }

    /** 获取时间格式为：YYYY年MM月DD日的时间字符串。 */
    public String getSysDateCN() {
        return sysDateCN;
    }

    /** 获取时间格式为：YYYY年MM月DD日 HH时MS分SS秒的时间字符串。 */
    public String getSysTimeCN() {
        return sysTimeCN;
    }

    private static String fillZero(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        }
        else {
            return String.valueOf(value);
        }
    }

    public static void main(String[] args) {
        SysTime st = new SysTime();
        System.out.println(st.getSysTime());
        System.out.println(st.getSysDate());
        System.out.println(st.getSysDateSlash());
        System.out.println(st.getSysTimeSemicolon());
        System.out.println(st.getSysDateCN());
        System.out.println(st.getSysTimeCN());
    }
}
