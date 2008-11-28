package com.conant.order.util;

import java.util.*;
import java.io.*;

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

public class ExceptionHelper {
    private static String fileName = "other/ErrorCode.properties";

    private static Properties exceptionMap = new java.util.Properties();

    static {
        loadFromFile();
    }

    private ExceptionHelper() {
    }

    private static void loadFromFile() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().
                             getResourceAsStream(fileName);
            exceptionMap.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExceptionReason(String errorId) {
        Object obj = exceptionMap.get(errorId);
        if (obj == null) {
            return errorId + ": [Unknown]";
        } else {
            return StringHelper.native2Unicode((String) obj);
        }
    }
}
