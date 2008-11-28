/**
 * QueryOrdersValidator.java
 * 2008-11-23
 * Administrator
 */
package com.conant.order.web.form;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.conant.order.vo.OrderQuerier;

/**
 * @author Administrator
 *
 */
public class QueryOrdersValidator implements Validator
{
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class clazz)
	{
		return OrderQuerier.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors)
	{
	}
}
