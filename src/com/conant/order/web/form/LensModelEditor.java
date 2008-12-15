/**
 * 
 */
package com.conant.order.web.form;

import java.beans.PropertyEditorSupport;
import java.util.Iterator;
import java.util.List;

import com.conant.order.util.Logger;
import com.conant.order.vo.LensModel;

/**
 * @author Administrator
 * 
 */
public class LensModelEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger("LensModelEditor",
			Logger.DEBUG, true);

	private List<LensModel> lensmodels;

	public LensModelEditor(List lensmodels)
	{
		this.lensmodels = lensmodels;
	}

	public LensModel getValue()
	{
		return (LensModel)super.getValue();
	}

	@Override
	public String getAsText()
	{
		LensModel model = getValue();
		return (model == null) ? "" : model.getLensmodel();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		log.info("LensModelEditor: setAsText-" + text);
		Integer modelId = null;
		for(LensModel model : lensmodels)
		{
			if(model.getLensmodel().equalsIgnoreCase(text))
			{
				modelId = Integer.valueOf(model.getId());
				break;
			}
		}
		try
		{
			LensModel value = getValue();
			value.setId(modelId);
		}
		catch(Exception e)
		{

		}
	}
}
