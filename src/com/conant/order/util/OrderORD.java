package com.conant.order.util;


import org.hibernate.criterion.Order;

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

public class OrderORD extends Order {

    public OrderORD(String propertyName, boolean ascending) {
        super(propertyName, ascending);
    }
}
