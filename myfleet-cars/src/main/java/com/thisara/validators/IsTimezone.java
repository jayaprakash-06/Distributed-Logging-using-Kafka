package com.thisara.validators;

import com.thisara.validators.impl.IsTimezoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/*
 * Copyright the original author.
 * 
 * @author Thisara Alawala
 * @author https://mytechblogs.com
 * @author https://www.youtube.com/channel/UCRJtsC5VYYhmKnEqAGLKc2A
 * @since 2021-05-30
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsTimezoneValidator.class)
@Documented
public @interface IsTimezone {

	String message() default "Invalid timezone";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };

}