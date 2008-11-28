/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.web.form;

import com.conant.order.vo.LensDetail;
import com.conant.order.vo.LensModel;
import com.conant.order.vo.OrsOrder;

/**
 * 
 * @author Administrator
 */
public class LensOrderForm extends OrderForm
{
	private LensDetail lensdetail;

	public LensOrderForm(OrsOrder order)
	{
	    super(order);
	    lensdetail = order.getLensdetail();
	}
	
	public LensOrderForm()
	{
		super();
		// initialize lensdetail
		lensdetail = new LensDetail();
		// initialize lensmodel
		lensdetail.setOrsorder(getOrder());
		lensdetail.setLensmodel(new LensModel());
		getOrder().setLensdetail(lensdetail);
		getOrder().setOrdertype(1);
		getOrder().setOrderstatus(1);
	}

	public LensDetail getLensdetail()
	{
		return lensdetail;
	}
}
