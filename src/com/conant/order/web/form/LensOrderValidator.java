package com.conant.order.web.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class LensOrderValidator extends OrderValidator {
	
	@Override
	public boolean supports(Class clazz) {
		return LensOrderForm.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		validateLensDetail((LensOrderForm)obj, errors);
	}
	
	public void validateLensDetail(LensOrderForm order, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "lensdetail.lensmodel", "LDLM_REQUIRED", "* lens type is required.");
		ValidationUtils.rejectIfEmpty(errors, "lensdetail.diameter", "LDDT_REQUIRED", "* diameter is required.");
	}
}
