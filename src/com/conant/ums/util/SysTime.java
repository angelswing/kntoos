package com.conant.ums.util;

import java.util.Calendar;

/** <p> ˵������ȡ��ǰʱ����ַ����� </p>*/
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
        sysDateCN = years + "��" + months + "��" + days + "��";
        sysTimeCN = sysDateCN + " " + hours + "ʱ" + minutes + "��" + seconds +
            "��";
    }

    /** ��ȡʱ���ʽΪ��YYYYMMDD��ʱ���ַ����� */
    public String getSysDay() {
        return sysDay;
    }

    /** ��ȡʱ���ʽΪ��HH:MS:SS��ʱ���ַ����� */
    public String getSysTime() {
        return sysTime;
    }

    /** ��ȡʱ���ʽΪ��YYYY-MM-DD��ʱ���ַ����� */
    public String getSysDate() {
        return sysDate;
    }

    /** ��ȡʱ���ʽΪ��YYYY/MM/DD��ʱ���ַ����� */
    public String getSysDateSlash() {
        return sysDateSlash;
    }

    /** ��ȡʱ���ʽΪ��YYYY-MM-DD HH:MS:SS��ʱ���ַ����� */
    public String getSysTimeSemicolon() {
        return sysTimeSemicolon;
    }

    /** ��ȡʱ���ʽΪ��YYYY��MM��DD�յ�ʱ���ַ����� */
    public String getSysDateCN() {
        return sysDateCN;
    }

    /** ��ȡʱ���ʽΪ��YYYY��MM��DD�� HHʱMS��SS���ʱ���ַ����� */
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
