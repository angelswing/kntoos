/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.QuerierResult;

/**
 * 
 * @author Administrator
 */
public interface OrderDao
{
	List getOrdersByUsername(String username) throws ProcessException;

	OrsOrder getOrder(int orderId) throws ProcessException;

	OrderQuerier getOrders(OrderQuerier querier) throws ProcessException;
	
	void saveOrder(OrsOrder order) throws ProcessException;

	//void insertOrder(OrsOrder order) throws ProcessException;

	//void updateOrder(OrsOrder order) throws ProcessException;

	void deleteOrder(OrsOrder order) throws ProcessException;

	void deleteOrders(List<OrsOrder> orders) throws ProcessException;
}
