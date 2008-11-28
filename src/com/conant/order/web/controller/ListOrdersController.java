/**
 * 
 */
package com.conant.order.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.conant.order.dao.OrderDao;

/**
 * @author Administrator
 *
 */
public class ListOrdersController implements Controller {

	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = "aaa";
		Map model = new HashMap();
		model.put("orderList", orderDao.getOrdersByUsername(username));
		return new ModelAndView("ListOrders", model);
	}
}
