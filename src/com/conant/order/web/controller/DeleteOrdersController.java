package com.conant.order.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.vo.LensDetail;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;

public class DeleteOrdersController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"DeleteOrdersController", Logger.DEBUG, true);
	private OrderDao orderDao;

	public DeleteOrdersController()
	{
		setCommandClass(OrderQuerier.class);
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("DeleteOrdersController onSubmit...");

		OrderQuerier querier = (OrderQuerier)command;

		List orders = new ArrayList();
		String[] ids = querier.getIds();
		if(ids != null)
		{
			log.debugT("DeleteOrdersController ids length === " + ids.length);
			OrsOrder order = null;
			for(String id : ids)
			{
				log.debugT("DeleteOrdersController ids === " + id);
				order = new OrsOrder();
				order.setId(Integer.parseInt(id));
				orders.add(order);
			}
		}

		PageMsg pageMsg = new PageMsg();

		try
		{
            orderDao.deleteOrders(orders);
            pageMsg.setMsg("Delete Orders successfully!");
            pageMsg.setUrl("/queryOrders.ord");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView("common/err", "error", pageMsg);
		}
	}
}
