/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.web.form;

import com.conant.order.vo.OrsOrder;
import java.io.Serializable;

/**
 * 
 * @author Administrator
 */
public class OrderForm implements Serializable
{
	private OrsOrder order;

	public OrderForm()
	{
		this.order = new OrsOrder();
	}
	
	public OrderForm(OrsOrder order)
	{
		this.order = order;
	}

	public OrsOrder getOrder()
	{
		return order;
	}
}
