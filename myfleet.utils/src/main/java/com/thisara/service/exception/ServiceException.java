package com.thisara.service.exception;

import com.thisara.exception.ErrorCodes;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorCodes errorCode;
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}
}
