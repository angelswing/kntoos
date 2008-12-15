/**
 * OrderTypeEditor.java
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
import com.conant.order.vo.OrderType;

/**
 * @author Administrator
 *
 */
public class OrderTypeEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"OrderTypeEditor", Logger.DEBUG, true);	
	private boolean allowEmpty;
	private static final Map<Integer, String> orderTypes;

	static
	{
		orderTypes = new HashMap<Integer, String>();
		orderTypes.put(OrderType.TYPE_LS, "Lens");
		orderTypes.put(OrderType.TYPE_FM, "Frame");
		orderTypes.put(OrderType.TYPE_FM_LS, "Frame And Lens");
	}
	
	public OrderTypeEditor(boolean allowEmpty)
	{
		this.allowEmpty = allowEmpty;
	}
	
	public static Map<Integer, String> getMapType()
	{
		return orderTypes;
	}
	
	public static List<String> getListType()
	{
		List<String> types = new ArrayList<String>();
        for(Map.Entry<Integer, String> entry : orderTypes.entrySet())
        {
        	types.add(entry.getValue());
        }
        
        return types;
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
