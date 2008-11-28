/**
 * OrderTypeEditor.java
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
public class OrderTypeEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"OrderTypeEditor", Logger.DEBUG, true);	
	private boolean allowEmpty;
	private Map<Integer, String> orderTypes;
	
	public OrderTypeEditor(boolean allowEmpty)
	{
		this.allowEmpty = allowEmpty;
		orderTypes = new HashMap<Integer, String>();
		orderTypes.put(1, "Lens");
		orderTypes.put(2, "Frame");
		orderTypes.put(3, "Frame And Lens");
	}
	
	@Override
	public String getAsText()
	{
		log.info("OrderTypeEditor getAsText " + getValue());
		Integer value = (Integer)getValue();
		return value != null ? orderTypes.get(value) : "";
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		log.info("OrderTypeEditor setAsText " + text);
		if(this.allowEmpty && !StringUtils.hasText(text))
		{
			// Treat empty String as null value.
			setValue(null);
			return;
		}
        for(Map.Entry<Integer, String> entry : orderTypes.entrySet())
        {
            if(entry.getValue().equalsIgnoreCase(text))
            {
            	setValue(entry.getKey());
            	break;
            }
        }
	}
}
