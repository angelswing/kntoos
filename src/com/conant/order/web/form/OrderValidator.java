package com.conant.order.web.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.conant.order.vo.OrsOrder;

public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return OrderForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		validateOrder((OrderForm)obj, errors);
	}
	
	public void validateOrder(OrderForm order, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "order.clientname", "CN_REQUIRED", "* client name required.");
		ValidationUtils.rejectIfEmpty(errors, "order.telephone", "TP_REQUIRED", "* telephone required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.ordereddate", "OD_REQUIRED", "* ordered date required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.requesteddate", "RD_REQUIRED", "* requested date required.");		
	}
}
