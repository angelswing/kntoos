package com.conant.order.web.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.conant.order.vo.OrsOrder;

public class OrderValidator implements Validator
{
	@Override
	public boolean supports(Class clazz)
	{
		return OrsOrder.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors)
	{
		validateOrder((OrsOrder)obj, errors);
	}

	public void validateOrder(OrsOrder order, Errors errors)
	{
		ValidationUtils.rejectIfEmpty(errors, "clientname",
				"CN_REQUIRED", "* client name required.");
		ValidationUtils.rejectIfEmpty(errors, "telephone", "TP_REQUIRED",
				"* telephone required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ordereddate",
				"OD_REQUIRED", "* ordered date required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"requesteddate", "RD_REQUIRED",
				"* requested date required.");
	}
}
