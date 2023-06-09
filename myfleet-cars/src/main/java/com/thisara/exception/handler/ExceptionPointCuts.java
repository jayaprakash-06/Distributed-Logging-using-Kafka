package com.thisara.exception.handler;

import org.aspectj.lang.annotation.Pointcut;


public class ExceptionPointCuts {

	@Pointcut("execution(* com.thisara.controller.exception.ExceptionController.handleException(..)) "
			+ "|| execution(* com.thisara.controller.car.exception.CarExceptionController.handleException(..))")
	public void exceptionHandller() {}
	
	@Pointcut("execution(* com.thisara.controller.car.exception.CarExceptionController.handleException(..))")
	public void carExceptionHandller() {}

}
