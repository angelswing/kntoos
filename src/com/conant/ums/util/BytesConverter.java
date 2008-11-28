package com.conant.ums.util;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * ����ID��BytesConverter.java<br> ���������ں����ֽ���ת���ġ�<br> ����String asc2gb(String asc)��ASCII����ַ�����ת��Ϊgb2312�ĺ����ַ�����<br>
 * ����String gb2asc(String gb)��gb2312�ĺ����ַ�����ת��ΪASCII����ַ�����<br> ʹ�÷�����<br> gbStr = BytesConverter.asc2gb(ascStr);<br>
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
     *��ASCII����ַ�����ת��Ϊgb2312�ĺ����ַ�����
     *@param asc��һ��ascii�ַ�������
     *@return gb2312����ĺ����ַ�����
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
     *��gb2312�ĺ����ַ�����ת��ΪASCII����ַ�����
     *@param gb��һ�������ַ�������
     *@return ASCII������ַ�����
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
     *���ֽ��ַ�����ת��Ϊinteger���ַ�����
     *@param b��һ���ֽ��ַ�������
     *@return integer���ַ�����
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
