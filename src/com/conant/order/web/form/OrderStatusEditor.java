/**
 * OrderStatusEditor.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.web.form;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;
import com.conant.order.vo.OrderStatus;

/**
 * @author Administrator
 *
 */
public class OrderStatusEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"OrderStatusEditor", Logger.DEBUG, true);
	
	private boolean allowEmpty;
	private static final Map<Integer, String> orderStatus;
	
	static
	{
		orderStatus = new HashMap<Integer, String>();
		orderStatus.put(OrderStatus.TYPE_PENDING, "pending");
		orderStatus.put(OrderStatus.TYPE_AUDITED, "audited");
		orderStatus.put(OrderStatus.TYPE_COMPLETE, "complete");
		orderStatus.put(OrderStatus.TYPE_DELIVERD, "delivered");
		orderStatus.put(OrderStatus.TYPE_DELAYED, "delayed");		
	}
	
	public OrderStatusEditor(boolean allowEmpty)
	{
		this.allowEmpty = allowEmpty;
	}
	
	public static Map<Integer, String> getMapStatus()
	{
		return orderStatus;
	}
	
	public static List<String> getListStatus()
	{
		List<String> listStatus = new ArrayList<String>();
        for(Map.Entry<Integer, String> entry : orderStatus.entrySet())
        {
        	listStatus.add(entry.getValue());
        }
        
        return listStatus;
	}
	
	@Override
	public String getAsText()
	{
		log.info("OrderStatusEditor getAsText " + getValue());
		Integer value = (Integer)getValue();
		return value != null ? orderStatus.get(value) : "";
	}

	@Override
	public void setAsText(String text)
	{
		log.info("OrderStatusEditor setAsText " + text);
		if(this.allowEmpty && !StringUtils.hasText(text))
		{
			// Treat empty String as null value.
			setValue(null);
			return;
		}
        for(Map.Entry<Integer, String> entry : orderStatus.entrySet())
        {
            if(entry.getValue().equalsIgnoreCase(text))
            {
            	setValue(entry.getKey());
            	break;
            }
        }
	}
}
