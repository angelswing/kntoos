package com.conant.ums.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright:</p>
 * @author:
 * @version 1.0
 */
public class ComString {

    public ComString() {
    }

    private final static String[] hexDigits = {
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};
    /**
     * ת���ֽ�����Ϊ16�����ִ�
     * @param b �ֽ�����
     * @return 16�����ִ�
     */

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    //��MD5��������
    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {
            System.err.print("error while make md5 string ----" + ex.getMessage());
        }
        return resultString;
    }

    //change string to "" if it is null
    public static String nvl(String sInput) {
        if (sInput == null) {
            return "";
        }
        else {
            return sInput;
        }
    }

    //���ַ���ǰ�油0,ֻ������ΪiLen
    public static String zeroFormat(String sInput, int iLen) {
        int iOrgLen = sInput.length();
        if (iOrgLen >= iLen) {
            return sInput;
        }
        else {
            for (int i = 0; i < iLen - iOrgLen; i++) {
                sInput = "0" + sInput;
            }
            return sInput;
        }
    }

    //���ַ������油0,ֻ������ΪiLen
    public static String zeroFormatAfter(String sInput, int iLen) {
        int iOrgLen = sInput.length();
        if (iOrgLen >= iLen) {
            return sInput;
        }
        else {
            for (int i = 0; i < iLen - iOrgLen; i++) {
                sInput = sInput + "0";
            }
            return sInput;
        }
    }

    //�����û�bitλ����Ƿ����ִ�иù���
    public static boolean checkFuncBit(String funcBit, String userBit) {
        boolean bRet = true;

        BigInteger biFunc = new BigInteger(funcBit.trim(), 16);
        BigInteger biUser = new BigInteger(userBit.trim(), 16);
        bRet = biFunc.and(biUser).equals(biFunc);
        return bRet;
    }

    //�����û�bitλ����Ƿ����ִ�иù���
    public static boolean checkFuncBit(String funcBit, BigInteger biUser) {
        boolean bRet = true;
        //modified by raokun 2005.02.01 begin.��fun_t��λԪ�����λ������������,
        //ͬʱ��DB��ԭ����char�͸�Ϊ��varchar��.
        //BigInteger biFunc = new BigInteger(funcBit,16);
        BigInteger biFunc = new BigInteger(funcBit.trim(), 16);
        //modified by raokun 2005.02.01 end.
        bRet = biFunc.and(biUser).equals(biFunc);
        return bRet;
    }

    public static void main(String[] args) {
        String sTest = "test";
        System.out.println("format string is " +
                           ComString.zeroFormatAfter(sTest, 6));
        System.out.println(MD5Encode("a"));
    }
}
