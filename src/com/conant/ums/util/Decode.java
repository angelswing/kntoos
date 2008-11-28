package com.conant.ums.util;

import java.util.Random;

/**
 * <p> 提供加密/解密处理函数: 加密函数: encrypt(String sPass),输入侍加密字串返回值为加密后的字串 解密函数: decrypt(String sCode),输入侍解密字串返回值为解密后的字串
 * 比对明文/密文匹配:DecodeMatch(String bfStr, String afStr), 输入为明文,密文,比对两者是否区配, 匹配时返回为true,否则返回为false </p>
 */
public class Decode {
    private static final int MAX_PASS_LEN = 256;
    private static char[] g_caCodeTbl = new char[65];

    public static void main(String[] args) {
        String inStr;
        if (args.length != 1) {
            inStr = "stone  xx";
            //inStr = null;
        }
        else {
            inStr = args[0];
        }

        /*
               String sTmp0 = "abcdefg ABC ^ & \\ *";
               System.out.println(sTmp0);
               System.out.println(sTmp0.length());

               String sTmp = string2hex(sTmp0);

               System.out.println(sTmp);
               System.out.println(sTmp.length());

         String sTmp1 = hex2string("6162636465666720414243205e2026205c202");
               System.out.println("hex2string=[" + sTmp1 +"]");
         */

        System.err.println("Input string is:\n" + inStr);
        String newStr = Decode.encrypt(inStr);
        System.err.println("The encrypted string is:\n" + newStr);
        String oldStr = Decode.decrypt(newStr);
        System.err.println("The decrypted string is:\n" + oldStr);
        if (DecodeMatch(inStr, newStr)) {
            System.err.println("Test successfully");
        }
        else {
            System.err.println("Test failed");
        }
    }

    /**
     *加密函数: encrypt(String sPass),输入侍加密字串返回值为加密后的字串
     *@sPass是侍加密字串
     */
    public static String encrypt(String sPass) {
        int i, j;
        int shift;
        long accum, value;
        char ucKey;
        String sCode = new String("");
        String sHexCode = new String("");

        //System.out.println("In  encrypt sPass=[" + sPass +"]");

        if (sPass == null) {
            return null;
        }

        int iPassLen = sPass.length();
        if (iPassLen == 0) {
            return sCode;
        }

        char[] pcaPass = sPass.toCharArray();
        //System.out.println("In  encrypt pcaPass=[" + pcaPass +"]");

        char[] pcaCode = new char[MAX_PASS_LEN];
        Initial_CodeTbl();
        ucKey = MakeKey();
        Rebuild_CodeTbl(ucKey);
        j = 1;
        accum = value = shift = 0;
        pcaCode[0] = (char) ucKey;
        //System.err.print("code[0]= "+pcaCode[j-1]+"\n");
        for (i = 0; i <= iPassLen; i++) {
            if (i != iPassLen) {
                value = (long) pcaPass[i];
                //System.err.println("pass["+i+"]="+pcaPass[i]+"  v="+value);
            }
            else {
                value = 0;
            }
            //value = (long) pcaPass[i];
            accum <<= 8;
            accum |= value;
            shift += 8;
            while (shift >= 6) {
                shift -= 6;
                value = (accum >> shift);
                value &= 0x3F;
                //System.err.print("v="+value);
                pcaCode[j++] = g_caCodeTbl[ (int) value];
                //System.err.println("code["+(j-1)+"]= "+pcaCode[j-1]);
            }
        }

        //System.out.println("Out encrypt =[" + pcaCode +"]");
        //System.err.println("\n end encode");
        //System.out.println("Out encrypt =[" + sCode.copyValueOf(pcaCode, 0, j) +"]");
        sHexCode = string2hex(sCode.copyValueOf(pcaCode, 0, j));
        return (sHexCode);
    }

    //End encode

    /**
     *加密函数: decrypt(String sCode),输入侍解密字串返回值为解密后的字串
     *@sCode是侍解密字串
     */
    //Begin decode
    public static String decrypt(String sHexCode) {
        int i, j;
        long accum, value, shift;
        char ucKey;
        String sPass = new String("");

        //System.out.println("In  decrypt =[" + sHexCode +"]");

        if (sHexCode == null) {
            return null;
        }

        String sCode = hex2string(sHexCode);
        //System.out.println("In  decrypt =[" + sCode +"]");

        int iCodeLen = sCode.length();
        if (iCodeLen == 0) {
            return sPass;
        }

        char[] caTmpCode = new char[MAX_PASS_LEN];
        char[] pcaCode = sCode.toCharArray();
        char[] pcaPass = new char[MAX_PASS_LEN];

        Initial_CodeTbl();
        //ucKey = GetKey(sCode);
        ucKey = pcaCode[0];
        //System.out.println("decrypt getKey =" + ucKey);
        Rebuild_CodeTbl(ucKey);
        //memcpy (caTmpCode, pcaCode+1, sizeof(caTmpCode));
        //for (i=0;i<iCodeLen-1;i++) caTmpCode[i] = pcaCode[i+1];
        //caTmpCode[i+1] = '\0';
        //for (i=0;i<iCodeLen;i++) System.err.println("caTmpCode["+i+"]="+caTmpCode[i]);
        accum = value = shift = j = 0;
        // for ( i = 0; i<iCodeLen; i++ )
        for (i = 1; i < iCodeLen; i++) {
            value = cvt_code(pcaCode[i]);
            //System.err.println("code=["+i+"]="+pcaCode[i]+"  v="+value);
            if (value < 64) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    value = accum >> shift;
                    value &= 0xFF;
                    //System.err.print("v="+value);
                    pcaPass[j++] = (char) value;
                    //System.err.println("pass=["+(j-1)+"]="+pcaPass[j-1]);
                }
            }
            else {
                break;
            }
        }
        //System.out.println("Out decrypt =[" + pcaPass + "]");

        if (pcaPass[j - 1] == 0) {
            return (sPass.copyValueOf(pcaPass, 0, j - 1));
        }
        else {
            return (sPass.copyValueOf(pcaPass, 0, j));
        }
    }

    /**
     *比对明文/密文匹配:DecodeMatch(String bfStr, String afStr),
     *@bfStr 为明文,
     *@afStr 为密文, 比对两者是否区配,匹配时返回为true,否则返回为false
     */
    public static boolean DecodeMatch(String bfStr, String afStr) {
        boolean bMatch = false;
        String tempStr = null;

        if (bfStr == null || afStr == null) {
            return false;
        }

        tempStr = decrypt(afStr);
        if (tempStr.compareTo(bfStr) == 0) {
            bMatch = true;
        }
        return bMatch;
    }

    // Begin Initial_codeTbl
    private static void Initial_CodeTbl() {
        int i;
        for (i = 0; i < 64; i++) {
            if (i == 0) {
                g_caCodeTbl[i] = 'A';
            }
            else if (i == 26) {
                g_caCodeTbl[i] = 'a';
            }
            else if (i == 52) {
                g_caCodeTbl[i] = '0';
            }
            else if (i == 62) {
                g_caCodeTbl[i] = '+';
            }
            else if (i == 63) {
                g_caCodeTbl[i] = '/';
            }
            else {
                g_caCodeTbl[i] = (char) (g_caCodeTbl[i - 1] + 1);
            }
            //System.err.print(g_caCodeTbl[i]);
        }
        //System.err.println("test init"+"\n");
    }

    // End Initial_codeTbl
    //Begin Rebuild_CodeTbl
    private static void Rebuild_CodeTbl(char ucKey) {
        int i, j;
        char[] caBuf = new char[65];
        //memset (caBuf, 0, sizeof(caBuf));
        for (i = 0; i < 64; i++) {
            caBuf[i] = 0;
        }
        while (ucKey >= 64) {
            ucKey -= 64;
        }
        for (i = ucKey, j = 0; i < 64; i++, j++) {
            caBuf[j] = g_caCodeTbl[i];
        }
        for (i = 0; i < ucKey; i++, j++) {
            caBuf[j] = g_caCodeTbl[i];
        }
        //memcpy (g_caCodeTbl, caBuf, sizeof(caBuf));
        for (i = 0; i < 64; i++) {
            g_caCodeTbl[i] = caBuf[i];
            //System.err.print(g_caCodeTbl[i]);
        }
    }

    //End Rebuild_CodeTbl
    //Begin cvt_code
    private static int cvt_code(char code) {
        int i;
        for (i = 0; i < 64; i++) {
            if (code == g_caCodeTbl[i]) {
                return (i);
            }
        }
        return ( -1);
    }

    //End cvt_code
    //Begin MakeKey
    private static char MakeKey() {
        //long lTime;
        char ucKey;
        long lRand;
        //System.err.println("Begin in makeKey");
        //lTime = Calendar.time;//java.util.Date
        //srand (iTime % 1000);
        Random random = new Random();
        lRand = (random.nextInt()) % 1000; //java.util.Random
        ucKey = (char) (lRand % 26 + 65);
        // System.err.println("iRand="+lRand);
        // System.err.println("ucKey="+ucKey);
        return (ucKey);
    }

    //End MakeKey
    //Begin GetKey
    private static char GetKey(String sCode) {
        char ucKey;
        char[] caCode = sCode.toCharArray();
        ucKey = caCode[0];
        return (ucKey);
    }

    //End GetKey

    private static String string2hex(String sIn) {
        if (sIn == null) {
            return null;
        }

        char[] pcaIn = sIn.toCharArray();
        StringBuffer sbHex = new StringBuffer(MAX_PASS_LEN * 2);
        String sOut = null;

        //char[] pcaHex = new char[MAX_PASS_LEN * 2];

        int iLen = sIn.length();
        for (int i = 0; i < iLen; i++) {
            String sHex = char2hex(pcaIn[i]);
            sbHex = sbHex.append(sHex);
        }
        sOut = sbHex.toString();
        return sOut;
    }

    private static String hex2string(String sIn) {
        if (sIn == null) {
            return null;
        }

        char[] pcaIn = sIn.toCharArray();
        StringBuffer sbString = new StringBuffer(MAX_PASS_LEN);
        String sOut = null;

        //char[] pcaHex = new char[MAX_PASS_LEN * 2];

        int iLen = sIn.length() / 2;
        //System.out.println("iLen=" + iLen);
        for (int i = 0; i < iLen; i++) {
            String sCh = sIn.substring(2 * i, 2 * i + 2);
            char cCh = hex2char(sCh);
            sbString = sbString.append(cCh);
        }
        sOut = sbString.toString();
        return sOut;
    }

    public static String char2hex(char c) {
        int iCh = ( ( -1) >>> 24) & ( (byte) c);
        String sHex = Integer.toHexString(iCh);

        return sHex;
    }

    public static char hex2char(String sIn) {
        if (sIn == null) {
            return 0;
        }
        char[] pcaIn = sIn.toLowerCase().toCharArray();
        int i0 = 0;
        int i1 = 0;

        int iLen = sIn.length();
        if (iLen > 0) {
            if (pcaIn[0] >= '0' && pcaIn[0] <= '9') {
                i0 = pcaIn[0] - '0';
            }
            else if (pcaIn[0] >= 'a' && pcaIn[0] <= 'f') {
                i0 = pcaIn[0] - 'a' + 10;
            }
            else {
                i0 = 0;
            }
        }

        if (iLen > 1) {
            if (pcaIn[1] >= '0' && pcaIn[1] <= '9') {
                i1 = pcaIn[1] - '0';
            }
            else if (pcaIn[1] >= 'a' && pcaIn[1] <= 'f') {
                i1 = pcaIn[1] - 'a' + 10;
            }
            else {
                i1 = 0;
            }
        }

        return (char) (i0 * 16 + i1);
    }

}
