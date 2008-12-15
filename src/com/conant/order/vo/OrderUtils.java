/**
 * OrderUtils.java
 * 2008-12-11
 * Administrator
 */
package com.conant.order.vo;


/**
 * @author Administrator
 *
 */
public class OrderUtils
{
	public static OrsOrder completeLensOrder(OrsOrder order)
	{
		if(order.getLensdetail() == null)
		{
			LensDetail detail = new LensDetail();
			detail.setLensmodel(new LensModel());
			detail.setOrsorder(order); 
			detail.setId(order.getId());
			order.setLensdetail(detail);
			if(order.getOrdertype() == null)
			{
				order.setOrdertype(OrderType.TYPE_LS);
			}
		}
		
		return order;
	}
	
	public static OrsOrder completeFrameOrder(OrsOrder order)
	{
		if(order.getFramedetail() == null)
		{
			FrameDetail detail = new FrameDetail();
			detail.setFramemodel(new FrameModel());
			detail.setOrsorder(order);
			detail.setId(order.getId());
			order.setFramedetail(detail);
			if(order.getOrdertype() == null)
			{
				order.setOrdertype(OrderType.TYPE_FM);
			}
		}
		
		return order;
	}
	
	public static OrsOrder completeOrder(OrsOrder order)
	{
		Integer orderType = order.getOrdertype();
		
		if(orderType == OrderType.TYPE_LS)
		{
			completeLensOrder(order);
		}
		else if(orderType == OrderType.TYPE_FM)
		{
			completeFrameOrder(order);
		}
		else
		{
			completeLensOrder(order);
			completeFrameOrder(order);
		}
		
		return order;
	}
}
