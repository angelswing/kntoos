package com.conant.order.util;

import java.lang.reflect.Method;
import java.lang.reflect.Array;

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

public class ToStringUtil {


    /**
     * 将数组元素属性转为字串
     * @param obj - 数组元素
     * @return - 属性串
     */
    private static String arrayToString(Object obj, String spaceString) {
        String newSpaceString = spaceString + "    ";

        StringBuffer sb = new StringBuffer();

        if (obj != null && obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            sb.append(newSpaceString + "Size:" + length + "\r\n");
            for (int j = 0; j < length; ++j) {
                sb.append(newSpaceString + "[" + j + "]:");
                Object objElement = Array.get(obj, j);
                if (objElement.getClass().isArray()) {
                    sb.append(arrayToString(objElement, newSpaceString));
                } else {
                    sb.append(newSpaceString + objElement);
                }
                sb.append(newSpaceString + "\r\n");
            }
        }

        return sb.toString();
    }

    /**
     * 以十进制输出byte流
     * @param bytes byte[]
     * @return String
     */
    public static String byteArrayToString(byte[] bytes) {
        if (null == bytes) {
            return null;
        }

        if (0 == bytes.length) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("In dec:\r\n");
        for (int i = 0; i < bytes.length; i++) {
            sb.append(bytes[i]);
            sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * 获取Object对象的值并转为字串输出
     * @param obj － 值对象
     * @return - 值串
     */
    public static String getvalueStringFromObject(Object obj) {
        String valueString = null;

        if (null == obj) {
            valueString = "";
        } else if (obj instanceof Boolean) {
            valueString = ((Boolean) obj).toString();
        } else if (obj instanceof Character) {
            valueString = ((Character) obj).toString();
        } else if (obj instanceof Byte) {
            valueString = ((Byte) obj).toString();
        } else if (obj instanceof Short) {
            valueString = ((Short) obj).toString();
        } else if (obj instanceof Integer) {
            valueString = ((Integer) obj).toString();
        } else if (obj instanceof Long) {
            valueString = ((Long) obj).toString();
        } else if (obj instanceof Float) {
            valueString = ((Float) obj).toString();
        } else if (obj instanceof Double) {
            valueString = ((Double) obj).toString();
        } else if (obj instanceof Void) {
            valueString = "";
        } else if (obj instanceof String) {
            valueString = ((String) obj);
        } else {
            // 这些对象应该是基本类型，所以不应该进入到这个语句中
            valueString = "";
        }

        return valueString;
    }

}
