package com.conant.order.vo;

// Generated 2008-11-16 19:21:15 by Hibernate Tools 3.2.1.GA

import java.util.Date;

public class OrsOrder implements java.io.Serializable
{
	private Integer id;
	private Integer exist;
	private Integer ordertype;
	private Integer orderstatus;
	private String clientname;
	private String telephone;
	private Date ordereddate;
	private Date requesteddate;
	private String referenceno;
	private String courier;
	private String couriercode;
	private Date deliverydate;
	private Date shipmentdate;
	private Date auditeddate;
	private String remarks;
	private LensDetail lensdetail;
	private FrameDetail framedetail;

	public OrsOrder()
	{
		setOrderstatus(OrderStatus.TYPE_PENDING);		
	}

	public OrsOrder(Integer exist, Integer ordertype, Integer orderstatus,
			String clientname, String telephone, Date ordereddate,
			Date requesteddate)
	{
		this.exist = exist;
		this.ordertype = ordertype;
		this.orderstatus = orderstatus;
		this.clientname = clientname;
		this.telephone = telephone;
		this.ordereddate = ordereddate;
		this.requesteddate = requesteddate;
	}

	public OrsOrder(Integer exist, Integer ordertype, Integer orderstatus,
			String clientname, String telephone, Date ordereddate,
			Date requesteddate, String referenceno, String courier,
			String couriercode, Date deliverydate, Date shipmentdate,
			Date auditeddate, String remarks, LensDetail lensdetail,
			FrameDetail framedetail)
	{
		this.exist = exist;
		this.ordertype = ordertype;
		this.orderstatus = orderstatus;
		this.clientname = clientname;
		this.telephone = telephone;
		this.ordereddate = ordereddate;
		this.requesteddate = requesteddate;
		this.referenceno = referenceno;
		this.courier = courier;
		this.couriercode = couriercode;
		this.deliverydate = deliverydate;
		this.shipmentdate = shipmentdate;
		this.auditeddate = auditeddate;
		this.remarks = remarks;
		this.lensdetail = lensdetail;
		this.framedetail = framedetail;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getExist()
	{
		return this.exist;
	}

	public void setExist(Integer exist)
	{
		this.exist = exist;
	}

	public Integer getOrdertype()
	{
		return this.ordertype;
	}

	public void setOrdertype(Integer ordertype)
	{
		this.ordertype = ordertype;
	}

	public Integer getOrderstatus()
	{
		return this.orderstatus;
	}

	public void setOrderstatus(Integer orderstatus)
	{
		this.orderstatus = orderstatus;
	}

	public String getClientname()
	{
		return this.clientname;
	}

	public void setClientname(String clientname)
	{
		this.clientname = clientname;
	}

	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public Date getOrdereddate()
	{
		return this.ordereddate;
	}

	public void setOrdereddate(Date ordereddate)
	{
		this.ordereddate = ordereddate;
	}

	public Date getRequesteddate()
	{
		return this.requesteddate;
	}

	public void setRequesteddate(Date requesteddate)
	{
		this.requesteddate = requesteddate;
	}

	public String getReferenceno()
	{
		return this.referenceno;
	}

	public void setReferenceno(String referenceno)
	{
		this.referenceno = referenceno;
	}

	public String getCourier()
	{
		return this.courier;
	}

	public void setCourier(String courier)
	{
		this.courier = courier;
	}

	public String getCouriercode()
	{
		return this.couriercode;
	}

	public void setCouriercode(String couriercode)
	{
		this.couriercode = couriercode;
	}

	public Date getDeliverydate()
	{
		return this.deliverydate;
	}

	public void setDeliverydate(Date deliverydate)
	{
		this.deliverydate = deliverydate;
	}

	public Date getShipmentdate()
	{
		return this.shipmentdate;
	}

	public void setShipmentdate(Date shipmentdate)
	{
		this.shipmentdate = shipmentdate;
	}

	public Date getAuditeddate()
	{
		return auditeddate;
	}

	public void setAuditeddate(Date auditeddate)
	{
		this.auditeddate = auditeddate;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public LensDetail getLensdetail()
	{
		return this.lensdetail;
	}

	public void setLensdetail(LensDetail lensdetail)
	{
		this.lensdetail = lensdetail;
	}

	public FrameDetail getFramedetail()
	{
		return this.framedetail;
	}

	public void setFramedetail(FrameDetail framedetail)
	{
		this.framedetail = framedetail;
	}
}
