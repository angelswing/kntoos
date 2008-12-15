/**
 * ViewOrderController.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.form.OrderStatusEditor;
import com.conant.order.web.form.OrderTypeEditor;

/**
 * @author Administrator
 * 
 */
public class ViewOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger("ViewOrderController",
			Logger.DEBUG, true);

	private OrderDao orderDao;

	public OrderDao getOrderDao()
	{
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}
	
	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("ViewOrderController formBackingObject...");
		
		try
		{
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			OrsOrder order = orderDao.getOrder(orderId);
			return order;
		}
		catch(Exception e)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setMsg(e.getMessage());
			pageMsg.setUrl("/queryOrders.ord");
			ModelAndView modelAndView = new ModelAndView("common/err", "error",
					pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("ViewOrderController initBinder...");
		
		super.initBinder(request, binder);
		binder.registerCustomEditor(Integer.class, "orderstatus",
				new OrderStatusEditor(true));
		binder.registerCustomEditor(Integer.class, "ordertype",
				new OrderTypeEditor(true));
	}
}
