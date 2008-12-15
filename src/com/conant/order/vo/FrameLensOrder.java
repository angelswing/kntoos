/**
 * FrameLensOrder.java
 * 2008-12-14
 * Administrator
 */
package com.conant.order.vo;

/**
 * @author Administrator
 *
 */
public class FrameLensOrder extends OrsOrder
{
	public FrameLensOrder()
	{
		setOrdertype(OrderType.TYPE_FM_LS);
		FrameDetail framedetail = new FrameDetail();
		framedetail.setOrsorder(this);
		framedetail.setFramemodel(new FrameModel());
		setFramedetail(framedetail);		
		LensDetail lensdetail = new LensDetail();
		lensdetail.setOrsorder(this);
		lensdetail.setLensmodel(new LensModel());
		setLensdetail(lensdetail);
	}
}
