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
	private static final Logger log = Logger.getLogger("QueryOrdersController",
			Logger.DEBUG, true);
	private OrderDao orderDao;
	private int pageSize;
	private boolean filterAuditedOrders;

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

	public boolean isFilterAuditedOrders()
	{
		return filterAuditedOrders;
	}

	public void setFilterAuditedOrders(boolean filterAuditedOrders)
	{
		this.filterAuditedOrders = filterAuditedOrders;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("QueryOrdersController formBackingObject...");
		
		OrderQuerier querier = new OrderQuerier();
		
		int pageNo = 0;
		int orderStatus = 0;
		int pageSize = this.pageSize;
		
		// 处理有请求参数的情况
		if(StringUtils.hasText(request.getParameter("pageNo")))
		{
			try
			{
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			catch(Exception exp)
			{
			}
		}
		querier.setPageNo(pageNo);
		if(StringUtils.hasText(request.getParameter("status")))
		{
			try
			{
				orderStatus = Integer.parseInt(request.getParameter("status"));
				querier.setOrderstatus(orderStatus);
			}
			catch(Exception exp)
			{
			}
		}
		if(StringUtils.hasText(request.getParameter("pageSize")))
		{
			try
			{
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			catch(Exception exp)
			{
			}
		}
		querier.setPageSize(pageSize);
		querier.setStartIndex(pageNo * pageSize);
		// 如果是form提交不执行查询操作
		if(!isFormSubmission(request))
		{
			try
			{
				// 用户名
				String user = (String)request.getSession().getAttribute("user_name");
				querier.setClientname(user);
				// 执行查询				
				orderDao.getOrders(querier);
			}
			catch(Exception exp)
			{
				PageMsg pageMsg = new PageMsg();
				pageMsg.setTarget("_self");
				pageMsg.setMsg(exp.getMessage());
				ModelAndView modelAndView = new ModelAndView("common/err", "error",
						pageMsg);
				throw new ModelAndViewDefiningException(modelAndView);
			}
		}

		return querier;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		log.info("QueryOrdersController referenceData...");

		Map<Object, Object> model = new HashMap<Object, Object>();

		List<String> listOrderstatus = new ArrayList<String>();
		listOrderstatus.add("--Please Select--");
		listOrderstatus.addAll(OrderStatusEditor.getListStatus());
		List<String> listOrdertype = new ArrayList<String>();
		listOrdertype.add("--Please Select--");
		listOrdertype.addAll(OrderTypeEditor.getListType());
		model.put("listOrderstatus", listOrderstatus);
		model.put("listOrdertype", listOrdertype);

		return model;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("QueryOrdersController initBinder...");
		
		super.initBinder(request, binder);
		binder.registerCustomEditor(Integer.class, "orderstatus",
				new OrderStatusEditor(true));
		binder.registerCustomEditor(Integer.class, "ordertype",
				new OrderTypeEditor(true));
		// 为List对象的指定字段注册editor
		binder.registerCustomEditor(Integer.class, "listOrder.orderstatus",
				new OrderStatusEditor(true));
		binder.registerCustomEditor(Integer.class, "listOrder.ordertype",
				new OrderTypeEditor(true));
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{

		log.info("QueryOrdersController onSubmit...");
		OrderQuerier querier = (OrderQuerier)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl("/queryOrders.ord");

		try
		{
			// 用户名
			String user = (String)request.getSession().getAttribute("user_name");
			querier.setClientname(user);
			// 执行查询
			orderDao.getOrders(querier);
			log.info("QueryOrdersController start rendering...");
			// 如果直接返回ModelAndView对象，需要手动调用referenceData
			// 调用showForm会自动调用
			//Map controlModel = errors.getModel(); 
			//controlModel.putAll(referenceData(request));
			//return new ModelAndView(this.getSuccessView(), controlModel);
			return showForm(request, errors, this.getSuccessView());
		}
		catch(ProcessException pe)
		{
			pageMsg.setMsg("Failed to query orders!");
			return new ModelAndView("common/err", "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView("common/err", "error", pageMsg);
		}
	}
}
