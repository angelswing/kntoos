/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.web.form;

import com.conant.order.vo.FrameDetail;
import com.conant.order.vo.LensModel;
import com.conant.order.vo.OrsOrder;

/**
 * 
 * @author Administrator
 */
public class FrameOrderForm extends OrderForm
{

	private FrameDetail framedetail;

	public FrameOrderForm(OrsOrder order)
	{
		super(order);
		framedetail = order.getFramedetail();
	}

	public FrameOrderForm()
	{
		super();
		framedetail = new FrameDetail();
		getOrder().setFramedetail(framedetail);
	}

	public FrameDetail getFramedetail()
	{
		return framedetail;
	}
}
