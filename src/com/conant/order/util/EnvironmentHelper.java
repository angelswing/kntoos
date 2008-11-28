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

public class EnvironmentHelper {
    private String homePath = "";

    private static EnvironmentHelper instance = new EnvironmentHelper();

    public static EnvironmentHelper getInstance() {
        return instance;
    }

    private EnvironmentHelper() {

        homePath = System.getProperty("ORD_HOME");
        if (homePath == null) {
            homePath = System.getProperty("user.home");
        }
        System.out.println("Home Path=" + homePath);

    }

    public String getHomePath() {
        return homePath;
    }

}
