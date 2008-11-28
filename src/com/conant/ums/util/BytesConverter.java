package com.conant.ums.util;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 程序ID：BytesConverter.java<br> 本类是用于汉字字节流转换的。<br> 方法String asc2gb(String asc)将ASCII码的字符串流转换为gb2312的汉字字符流。<br>
 * 方法String gb2asc(String gb)将gb2312的汉字字符串流转换为ASCII码的字符流。<br> 使用方法：<br> gbStr = BytesConverter.asc2gb(ascStr);<br>
 * ascStr = BytesConverter.asc2gb(gbStr);<br></p>
 */
public class BytesConverter {

    private String ConvertInDbFrom = null;
    private String ConvertInDbTo = null;
    private String ConvertOutDbFrom = null;
    private String ConvertOutDbTo = null;

    public BytesConverter() {
        this.ConvertInDbFrom = null;
        this.ConvertInDbTo = null;
        this.ConvertOutDbFrom = null;
        this.ConvertOutDbTo = null;
    }

    public BytesConverter(
        String ConvertInDbFrom,
        String ConvertInDbTo,
        String ConvertOutDbFrom,
        String ConvertOutDbTo
        ) {
        this.ConvertInDbFrom = ConvertInDbFrom;
        this.ConvertInDbTo = ConvertInDbTo;
        this.ConvertOutDbFrom = ConvertOutDbFrom;
        this.ConvertOutDbTo = ConvertOutDbTo;
    }

    /**
     *将ASCII码的字符串流转换为gb2312的汉字字符流。
     *@param asc是一个ascii字符串流。
     *@return gb2312编码的汉字字符流。
     */
    public static String asc2gb(String asc) {
        String ret;
        if (asc == null) {
            return asc;
        }
        try {
            ret = new String(asc.getBytes("ISO8859_1"), "GB2312");
        }
        catch (UnsupportedEncodingException e) {
            ret = asc;
        }
        return ret;
    }

    /**
     *将gb2312的汉字字符串流转换为ASCII码的字符流。
     *@param gb是一个汉字字符串流。
     *@return ASCII编码的字符流。
     */
    public static String gb2asc(String gb) {
        String ret;
        if (gb == null) {
            return gb;
        }
        try {
            ret = new String(gb.getBytes("GB2312"), "ISO8859_1");
        }
        catch (UnsupportedEncodingException e) {
            ret = gb;
        }
        return ret;
    }

    /**
     *将字节字符串流转换为integer的字符流。
     *@param b是一个字节字符串流。
     *@return integer的字符流。
     */
    public static int byte2int(byte b) {
        return ( ( -1) >>> 24) & b;
    }

    public String convertInDb(String strIn) {
        return (convert(strIn, this.ConvertInDbFrom, this.ConvertInDbTo));
    }

    public String convertOutDb(String strIn) {
        return (convert(strIn, this.ConvertOutDbFrom, this.ConvertOutDbTo));
    }

    public static String convert(String strIn, String cvtFrom, String cvtTo) {
        String strRt = null;

        Log.debug("strIn =" + strIn + ";cvtFrom =" + cvtFrom + ";cvtTo =" +
                  cvtTo);

        if (strIn == null) {
            return null;
        }

        if (cvtFrom == null) {
            strRt = convert(strIn, cvtTo);
        }
        else if (cvtTo == null || cvtFrom.equalsIgnoreCase(cvtTo)) {
            strRt = strIn;
        }
        else {
            try {
                strRt = new String(strIn.getBytes(cvtFrom), cvtTo);
            }
            catch (UnsupportedEncodingException e) {
                strRt = strIn;
            }
        }

        return strRt;
    }

    public static String convert(String strIn, String cvtTo) {
        String strRt = null;

        if (strIn == null) {
            return null;
        }

        if (cvtTo == null) {
            strRt = strIn;
        }
        else {
            try {
                strRt = new String(strIn.getBytes(), cvtTo);
            }
            catch (UnsupportedEncodingException e) {
                strRt = strIn;
            }
        }

        return strRt;
    }

}
