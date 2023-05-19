package com.thisara.validators.impl;

import java.time.ZoneId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.thisara.validators.IsTimezone;


public class IsTimezoneValidator implements ConstraintValidator<IsTimezone, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean isValidTimezone = (value !=null && ZoneId.getAvailableZoneIds().contains(value));
		
		return isValidTimezone;
	}
}