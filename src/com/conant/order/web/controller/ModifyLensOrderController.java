package com.conant.order.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.conant.order.dao.LensModelDao;
import com.conant.order.util.Logger;
import com.conant.order.vo.LensModel;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.form.LensModelEditor;

public class ModifyLensOrderController extends ModifyOrderController
{
	private static final Logger log = Logger.getLogger(
			"ModifyLensOrderController", Logger.DEBUG, true);
	private LensModelDao lensModelDao;
	private List lensmodels;

	public ModifyLensOrderController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

	public LensModelDao getLensModelDao()
	{
		return lensModelDao;
	}

	public void setLensModelDao(LensModelDao lensModelDao)
	{
		this.lensModelDao = lensModelDao;
	}

	public Object formSubmissionBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		OrsOrder order = new OrsOrder();
		OrderUtils.completeLensOrder(order);
		
		return order;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		lensmodels = lensModelDao.getLensModel();
		model.put("lensmodels", lensmodels);
		return model;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		super.initBinder(request, binder);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		// CustomDateEditor should be set allowEmpty, otherwise it would throw
		// exception
		// before validator can handle invalid values.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(LensModel.class, new LensModelEditor(
				lensmodels));
	}
}
