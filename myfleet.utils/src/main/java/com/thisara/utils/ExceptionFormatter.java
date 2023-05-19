package com.thisara.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.thisara.exception.ErrorCodes;
import com.thisara.exception.response.ValidationError;
import com.thisara.exception.response.ValidationErrorResponse;
import com.thisara.utils.response.ErrorResponse;


public class ExceptionFormatter {

	public ResponseEntity<ErrorResponse> composeErrorResponse(ErrorCodes errorcode, String message, HttpStatus httpStatus){

		ResponseEntity<ErrorResponse> resposeEntity = new ResponseEntity<ErrorResponse>(
				new ErrorResponse(
						ErrorIDGenerator.getErrorId(),
						errorcode.name(),
						message,
						httpStatus.toString(), 
						LocalDateTime.now().toString()), httpStatus);
		
		return resposeEntity;
	}
	
	public ResponseEntity<ErrorResponse> composeValidationErrorResponse(ErrorCodes errorcode, String message, List<ValidationError> validationErrors, HttpStatus httpStatus){

		ResponseEntity<ErrorResponse> resposeEntity = new ResponseEntity<ErrorResponse>(
				new ValidationErrorResponse(
						ErrorIDGenerator.getErrorId(),
						errorcode.name(),
						message,
						validationErrors, 
						httpStatus.toString(), 
						LocalDateTime.now().toString()), httpStatus);
		
		return resposeEntity;
	}
}
