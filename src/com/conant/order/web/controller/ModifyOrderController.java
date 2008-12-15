/**
 * ModifyOrderController.java
 * 2008-12-14
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrsOrder;

/**
 * @author Administrator
 */
public abstract class ModifyOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger("ModifyOrderController",
			Logger.DEBUG, true);
	private OrderDao orderDao;

	public ModifyOrderController()
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

	public abstract Object formSubmissionBackingObject(
			HttpServletRequest request) throws ModelAndViewDefiningException;

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		if(isFormSubmission(request))
		{
			return formSubmissionBackingObject(request);
		}
		String orderId = request.getParameter("orderId");
		try
		{
			OrsOrder order = getOrderDao().getOrder(Integer.parseInt(orderId));
			return order;
		}
		catch(ProcessException pe)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/queryOrders.ord");
			throw new ModelAndViewDefiningException(new ModelAndView(
					"common/err", "error", pageMsg));
		}
	}
	
	// Use onSubmit instead of doSubmitAction
	// when you need access to the Request, Response, or BindException objects
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("ModifyOrderController onSubmit...");
		OrsOrder order = (OrsOrder)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl("/queryOrders.ord");

		try
		{
			getOrderDao().saveOrder(order);
			pageMsg.setMsg("Update order successfully!");
			
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
