package com.conant.order.util;

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

public class StringHelper {

    /**
     * @roseuid 3F864A3E030D
     */
    public StringHelper() {

    }

    private static String toHexString(byte i) {
        char[] buf = new char[2];
        char[] digits = {
                        '0', '1', '2', '3', '4', '5',
                        '6', '7', '8', '9', 'A', 'B',
                        'C', 'D', 'E', 'F'};

        buf[1] = digits[i & 0x0F];
        i >>>= 4;
        buf[0] = digits[i & 0x0F];

        return new String(buf);
    }

    public static String getHexString(byte[] buf) {
        if (buf == null)
            return "null";

        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < buf.length; i++) {
            if (i % 16 == 0)
                buff.append('\n');
            buff.append(toHexString(buf[i]) + " ");
        }
        buff.append('\n');
        return buff.toString();
    }

    /**
     * 将Unicode编码的字符串转化为Native编码的字符
     * @param str
     * @return String
     * @roseuid 3F8644F30222
     */
    public static String unicode2Native(String str) {
        return null;
    }

    /**
     * 将Native编码的字符串转化为Unicode编码的字符
     * @param str
     * @return String
     */
    public static String native2Unicode(String str) {
        try {
            byte[] buffer = new byte[str.length() * 2];
            int j = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 0x100) {
                    char c = str.charAt(i);

                    byte[] buf = ("" + c).getBytes();
                    buffer[j++] = (byte) buf[0];
                    buffer[j++] = (byte) buf[1];
                } else {
                    buffer[j++] = (byte) str.charAt(i);
                }
            }
            return new String(buffer, 0, j);
        } catch (Exception e) {
            byte[] b = str.getBytes();
            try {
                return new String(b, "gb2312");
            } catch (Exception e1) {
                return "";
            }
        }
    }

    public static String getBinaryStr(byte[] buf) {
        if (buf == null)
            return "null";

        StringBuffer buff = new StringBuffer();
        int k = 0;
        for (int i = 0; i < buf.length; i++) {
            short t = buf[i];
            if (t < 0) {
                t += 256;
            }
            {
                ;
            }
            if (t < 16)
                buff.append("0" + Integer.toString(t, 16) + " ");
            else
                buff.append(Integer.toString(t, 16) + " ");
            k++;
            if (k == 16) {
                k = 0;
                buff.append("\n");
            }
        }
        buff.append("\n");
        return buff.toString();
    }

}
