package com.conant.order.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.form.OrderStatusEditor;
import com.conant.order.web.form.OrderTypeEditor;

public class DeleteOrdersController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"DeleteOrdersController", Logger.DEBUG, true);
	private OrderDao orderDao;

	public DeleteOrdersController()
	{
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception
	{
		log.info("DeleteOrdersController formBackingObject...");
		
		OrderQuerier querier = new OrderQuerier();
		if(isFormSubmission(request))
		{
			// 绑定List对象需要使用LazyList，否则无法绑定
			List listOrder = ListUtils.lazyList(new ArrayList(), new Factory()
			{
				public Object create()
				{
					return new OrsOrder();
				}
			});
			querier.setListOrder(listOrder);
		}
		return querier;
	}
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("DeleteOrdersController initBinder...");
		
		super.initBinder(request, binder);
		// 为List对象的指定字段注册editor
		binder.registerCustomEditor(Integer.class, "listOrder.orderstatus",
				new OrderStatusEditor(true));
		binder.registerCustomEditor(Integer.class, "listOrder.ordertype",
				new OrderTypeEditor(true));
	}

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("DeleteOrdersController onSubmit...");

		OrderQuerier querier = (OrderQuerier)command;
		List<OrsOrder> orders = querier.getListOrder();
		
		log.info("DeleteOrdersController ids length === " + orders.size());
		// 过滤LazyList
		List<OrsOrder> selectOrders = new ArrayList<OrsOrder>();
		for(Iterator<OrsOrder> iter = orders.iterator(); iter.hasNext();)
		{
			OrsOrder order = iter.next();
			// 过滤null对象和id字段为null的对象
			if((order != null) 
					&& (order.getId() != null))
			{
				selectOrders.add(order);
			}
		}
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl("/queryOrders.ord");
		pageMsg.setTarget("_self");

		try
		{
			orderDao.deleteOrders(selectOrders);
			pageMsg.setMsg("Delete Orders successfully!");
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
