/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conant.order.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.LensModelDao;
import com.conant.order.util.Logger;
import com.conant.order.vo.LensModel;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.form.LensModelEditor;

/**
 * 
 * @author Administrator
 */
public class AddLensOrderController extends AddOrderController
{
	private static final Logger log = Logger.getLogger(
			"AddOrderFormController", Logger.DEBUG, true);
	private LensModelDao lensModelDao;
	private List lensmodels;

	public AddLensOrderController()
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

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("AddOrderFormController formBackingObject...");
		
		OrsOrder order = new OrsOrder();
		OrderUtils.completeLensOrder(order);
		String user = (String)request.getSession().getAttribute("user_name");
		if(!StringUtils.hasText(user))
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setMsg("Please login first!");
			ModelAndView modelAndView = new ModelAndView("common/err", "error", pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);
		}
		order.setClientname(user);
		
		return order;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
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
