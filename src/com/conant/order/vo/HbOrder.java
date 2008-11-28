package com.conant.order.vo;

import org.hibernate.criterion.Order;

public class HbOrder extends Order
{
    public HbOrder(String propertyName, boolean ascending)
    {
        super(propertyName, ascending);
    }
}
