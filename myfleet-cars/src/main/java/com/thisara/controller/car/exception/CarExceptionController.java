package com.thisara.controller.car.exception;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thisara.controller.exception.ExceptionController;
import com.thisara.exception.ErrorCodes;
import com.thisara.utils.ExceptionFormatter;
import com.thisara.utils.response.ErrorResponse;


@ControllerAdvice("com.thisara.controller.car")
public class CarExceptionController extends ExceptionController{

	private Logger logger = LoggerFactory.getLogger(CarExceptionController.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ExceptionFormatter exceptionFormatter;

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException e) {
		logger.error(e.getMessage());
		return exceptionFormatter.composeErrorResponse(ErrorCodes.CODAT002, "Record already exist", HttpStatus.BAD_REQUEST);
	}
}
