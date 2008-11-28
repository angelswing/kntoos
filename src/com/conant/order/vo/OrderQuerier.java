/**
 * OrderQuerier.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.vo;

import java.io.Serializable;

import org.hibernate.criterion.Order;

/**
 * @author Administrator
 * 
 */
public class OrderQuerier implements Serializable
{
	private Integer id;
	private Integer ordertype;
	private Integer orderstatus;
	private String clientname;
	private String telephone;
	private String ordereddate;
	private String requesteddate;
	private String remarks;
	private String[] ids;

	private Order[] orders;
	private int pageSize;
	private int startIndex;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getOrdertype()
	{
		return ordertype;
	}

	public void setOrdertype(Integer ordertype)
	{
		this.ordertype = ordertype;
	}

	public Integer getOrderstatus()
	{
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus)
	{
		this.orderstatus = orderstatus;
	}

	public String getClientname()
	{
		return clientname;
	}

	public void setClientname(String clientname)
	{
		this.clientname = clientname;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getOrdereddate()
	{
		return ordereddate;
	}

	public void setOrdereddate(String ordereddate)
	{
		this.ordereddate = ordereddate;
	}

	public String getRequesteddate()
	{
		return requesteddate;
	}

	public void setRequesteddate(String requesteddate)
	{
		this.requesteddate = requesteddate;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public Order[] getOrders()
	{
		return orders;
	}

	public void setOrders(Order[] orders)
	{
		this.orders = orders;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getStartIndex()
	{
		return startIndex;
	}

	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}
	
	public String[] getIds()
	{
		return ids;
	}

	public void setIds(String[] ids)
	{
		this.ids = ids;
	}	
}
