package com.conant.order.common;

import java.util.HashMap;
import java.util.ArrayList;


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

public class SessionMap {

    private static HashMap map = null;
    private static ArrayList list = null;


    public static HashMap getOneInstance() {
        if (null == map){
            map = new HashMap();
        }
        return map;
    }

    public static ArrayList getOneTreeInstance() {
        if (null == list){
            //System.out.println("list is null");
            list = new ArrayList();
        }
        return list;
    }

}
