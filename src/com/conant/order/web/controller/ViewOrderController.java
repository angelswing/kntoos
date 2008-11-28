/**
 * ViewOrderController.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.vo.OrsOrder;

/**
 * @author Administrator
 *
 */
public class ViewOrderController implements Controller {

	private static final Logger log = Logger.getLogger("ViewOrderController", Logger.DEBUG, true);
	
	private OrderDao orderDao;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try
		{
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			OrsOrder order = orderDao.getOrder(orderId); 
			log.info("ViewOrderController handleRequest order: " + order.getLensdetail() + " " + order.getLensdetail().getLensmodel());
			return new ModelAndView("ViewOrder", "order", order);
		}
		catch(Exception e)
		{
			PageMsg pageMsg = new PageMsg();
            pageMsg.setMsg(e.getMessage());
            pageMsg.setUrl("listOrders.ord");
            return new ModelAndView("common/err", "error", pageMsg);
		}
	}
}
