/**
 * AddOrderController.java
 * 2008-12-14
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrsOrder;

/**
 * @author Administrator
 *
 */
public class AddOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"AddOrderController", Logger.DEBUG, true);
	private OrderDao orderDao;
	
	public AddOrderController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

	public OrderDao getOrderDao()
	{
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}
	
	// Use onSubmit instead of doSubmitAction
	// when you need access to the Request, Response, or BindException objects
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{

		log.info("AddOrderController onSubmit...");
		OrsOrder order = (OrsOrder)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl("/addLensOrder.ord");

		try
		{
			orderDao.saveOrder(order);
			pageMsg.setMsg("Add order successfully!");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setMsg(pe.getErrorReason());
			return new ModelAndView("common/err", "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView("common/err", "error", pageMsg);
		}
	}
}
