/**
 * OrderStatusEditor.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.web.form;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;

/**
 * @author Administrator
 *
 */
public class OrderStatusEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"OrderStatusEditor", Logger.DEBUG, true);
	
	private boolean allowEmpty;
	private Map<Integer, String> orderStatus;
	
	public OrderStatusEditor(boolean allowEmpty)
	{
		this.allowEmpty = allowEmpty;
		orderStatus = new HashMap<Integer, String>();
		orderStatus.put(1, "create");
		orderStatus.put(2, "pending");
		orderStatus.put(3, "confirmed");
		orderStatus.put(4, "delivered");
		orderStatus.put(5, "delaied");
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
