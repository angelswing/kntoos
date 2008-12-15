/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.HbOrder;
import com.conant.order.vo.LensDetail;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.QuerierResult;

/**
 * 
 * @author Administrator
 */
public class OrderDaoImpl implements OrderDao
{

	private static final Logger log = Logger.getLogger("OrderDaoImpl",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	public List getOrdersByUsername(String username) throws ProcessException
	{
		List list = template
				.find("from OrsOrder orsorder where orsorder.clientname = '"
						+ username + "'");
		return list;
	}

	public OrsOrder getOrder(int orderId) throws ProcessException
	{
		OrsOrder order = null;
		try
		{
			order = (OrsOrder)template.load(OrsOrder.class, new Integer(orderId));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}

		return order;
	}
	
	public void saveOrder(OrsOrder order) throws ProcessException
	{
		try
		{
			if(order != null)
			{
				template.saveOrUpdate(order);
			}
			else
			{
				throw new ProcessException(217001);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public void deleteOrder(OrsOrder order) throws ProcessException
	{
		try
		{
			if(order != null)
			{
				template.delete(order);
			}
			else
			{
				throw new ProcessException(217001);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public void deleteOrders(List<OrsOrder> orders) throws ProcessException
	{
		try
		{
			if(orders != null && orders.size() > 0)
			{
				List<OrsOrder> listOrders = new ArrayList<OrsOrder>();
				for(Object order : orders)
				{
					OrsOrder item = getOrder(((OrsOrder)order).getId());
					if(item != null)
					{
						listOrders.add((OrsOrder)item);
					}
				}
				template.deleteAll(listOrders);
			}
			else
			{
				throw new ProcessException(217001);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public OrderQuerier getOrders(OrderQuerier querier)
			throws ProcessException
	{
		DetachedCriteria queryCriteria = DetachedCriteria.forClass(OrsOrder.class);
		DetachedCriteria countCriteria = DetachedCriteria.forClass(OrsOrder.class);

		try
		{
			if(querier == null)
			{
				throw new ProcessException(217001);
			}
			// query by field
			// by id
			if(querier.getId() != null)
			{
				queryCriteria.add(Restrictions.eq("id", Integer
						.valueOf(querier.getId())));
				countCriteria.add(Restrictions.eq("id", Integer
						.valueOf(querier.getId())));
			}
			// by order type
			if(querier.getOrdertype() != null)
			{
				log.debugT("OrderDaoImpl: order type === " + querier.getId());
				queryCriteria.add(Restrictions.eq("ordertype", Integer
						.valueOf(querier.getOrdertype())));
				countCriteria.add(Restrictions.eq("ordertype", Integer
						.valueOf(querier.getOrdertype())));
			}
			// by order status
			if(querier.getOrderstatus() != null)
			{
				log.debugT("OrderDaoImpl: order status === "
						+ querier.getOrderstatus());
				queryCriteria.add(Restrictions.eq("orderstatus", Integer
						.valueOf(querier.getOrderstatus())));
				countCriteria.add(Restrictions.eq("orderstatus", Integer
						.valueOf(querier.getOrderstatus())));
			}
			// by client name
			if(querier.getClientname() != null)
			{
				log.debugT("OrderDaoImpl: client name === "
						+ querier.getClientname());
				queryCriteria.add(Restrictions.eq("clientname", querier
						.getClientname()));
				countCriteria.add(Restrictions.eq("clientname", querier
						.getClientname()));
			}
			// by telephone
			if(querier.getTelephone() != null)
			{
				log.debugT("OrderDaoImpl: telephone === "
						+ querier.getTelephone());
				queryCriteria.add(Restrictions.eq("telephone", querier
						.getTelephone()));
				countCriteria.add(Restrictions.eq("telephone", querier
						.getTelephone()));
			}
			// by ordered date
			if(querier.getOrdereddate() != null)
			{
				log.debugT("OrderDaoImpl: ordered date === "
						+ querier.getClientname());
				queryCriteria.add(Restrictions.eq("ordereddate", querier
						.getOrdereddate()));
				countCriteria.add(Restrictions.eq("ordereddate", querier
						.getOrdereddate()));
			}
			// by requested date
			if(querier.getRequesteddate() != null)
			{
				log.debugT("OrderDaoImpl: requested date === "
						+ querier.getClientname());
				queryCriteria.add(Restrictions.eq("requesteddate", querier
						.getRequesteddate()));
				countCriteria.add(Restrictions.eq("requesteddate", querier
						.getRequesteddate()));
			}
			// by remarks
			if(querier.getRemarks() != null)
			{
				log.debugT("OrderDaoImpl: remarks === " + querier.getRemarks());
				queryCriteria.add(Restrictions.eq("remarks", querier
						.getRemarks()));
				countCriteria.add(Restrictions.eq("remarks", querier
						.getRemarks()));
			}
			// audited order
			if(querier.isFilterAuditedOrders())
			{
				log.debugT("OrderDaoImpl: filter audited orders ");
				queryCriteria.add(Restrictions.isNotNull("auditeddate"));
				countCriteria.add(Restrictions.isNotNull("auditeddate"));
			}
			// query total count
			Session session = template.getSessionFactory().openSession();
			int recordCount = ((Integer)countCriteria.getExecutableCriteria(
					session).setProjection(Projections.rowCount())
					.uniqueResult()).intValue();
			session.close();
			log.debugT("OrderDaoImpl: totalCount === " + recordCount);
			// order by
			if(querier.getOrders() != null)
			{
				for(Order order : querier.getOrders())
				{
					if(order != null)
					{
						queryCriteria.addOrder(order);
					}
				}
			}
			else
			{
				Order idOrder = new HbOrder("id", false);
				querier.setOrders(new Order[]
				{ idOrder });
			}
			// pagination
			if(querier.getStartIndex() < 0)
			{
				querier.setStartIndex(0);
			}
			if(querier.getPageSize() <= 0)
			{
				querier.setPageSize(20);
			}
			List list = template.findByCriteria(queryCriteria, querier
					.getStartIndex(), querier.getPageSize());
			querier.setListOrder(list);
			querier.setRecordCount(recordCount);
			if(recordCount >= 0)
			{
				if(querier.getPageSize() > querier.getRecordCount())
				{
					querier.setPageCount(1);
				}
				else
				{
					int pageCount = recordCount / querier.getPageSize();
					if(recordCount % querier.getPageSize() != 0)
					{
						pageCount++;
					}
					querier.setPageCount(pageCount);
				}
			}

			return querier;
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}
}
