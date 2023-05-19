package com.thisara.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.thisara.validators.impl.IsTimezoneValidator;


@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsTimezoneValidator.class)
@Documented
public @interface IsTimezone {

	String message() default "Invalid timezone";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };

}