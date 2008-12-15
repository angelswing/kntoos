/**
 * OrderQuerier.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.vo;

import java.io.Serializable;
import java.util.List;

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
	private int pageNo;
	private int pageSize;
	private int pageCount;
	private int startIndex;
	private List<OrsOrder> listOrder;
	private int recordCount;
	private boolean filterAuditedOrders;

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

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
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

	public List<OrsOrder> getListOrder()
	{
		return listOrder;
	}

	public void setListOrder(List<OrsOrder> listOrder)
	{
		this.listOrder = listOrder;
	}

	public int getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(int recordCount)
	{
		this.recordCount = recordCount;
	}

	public boolean isFilterAuditedOrders()
	{
		return filterAuditedOrders;
	}

	public void setFilterAuditedOrders(boolean filterAuditedOrders)
	{
		this.filterAuditedOrders = filterAuditedOrders;
	}
}
