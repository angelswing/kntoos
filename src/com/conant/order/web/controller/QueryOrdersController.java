/**
 * QueryOrdersController.java
 * 2008-11-23
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.QuerierResult;
import com.conant.order.web.form.OrderStatusEditor;
import com.conant.order.web.form.OrderTypeEditor;

/**
 * @author Administrator
 *
 */
public class QueryOrdersController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"QueryOrdersController", Logger.DEBUG, true);
	private OrderDao orderDao;
	private int pageSize;

	public QueryOrdersController()
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
	
	public int getPageSize()
	{
		return pageSize;
	}
	
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("QueryOrdersController formBackingObject...");
		return new OrderQuerier();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		log.info("QueryOrdersController referenceData...");
		
		Map<Object, Object> model = new HashMap<Object, Object>();
		
		List<String> orderstatusList = new ArrayList<String>();
		orderstatusList.add("--Please Select--");
		orderstatusList.add("create");
		orderstatusList.add("pending");
		orderstatusList.add("confirmed");
		orderstatusList.add("delivered");
		orderstatusList.add("delaied");
		List<String> ordertypeList = new ArrayList<String>();
		ordertypeList.add("--Please Select--");
		ordertypeList.add("Lens");
		ordertypeList.add("Frame");
		ordertypeList.add("Frame And Lens");
		model.put("orderstatusList", orderstatusList);
		model.put("ordertypeList", ordertypeList);
		
		return model;
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
	{
		log.info("QueryOrdersController initBinder...");
		super.initBinder(request, binder);
		binder.registerCustomEditor(Integer.class, "orderstatus", new OrderStatusEditor(true));
		binder.registerCustomEditor(Integer.class, "ordertype", new OrderTypeEditor(true));
	}
}
