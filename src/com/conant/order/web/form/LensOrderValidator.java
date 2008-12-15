package com.conant.order.web.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.conant.order.vo.OrsOrder;

public class LensOrderValidator extends OrderValidator
{
	public void validate(Object obj, Errors errors)
	{
		super.validate(obj, errors);
		validateLensDetail((OrsOrder)obj, errors);
	}

	public void validateLensDetail(OrsOrder order, Errors errors)
	{
		ValidationUtils.rejectIfEmpty(errors, "lensdetail.lensmodel",
				"LDLM_REQUIRED", "* lens type is required.");
		ValidationUtils.rejectIfEmpty(errors, "lensdetail.diameter",
				"LDDT_REQUIRED", "* diameter is required.");
	}
}
