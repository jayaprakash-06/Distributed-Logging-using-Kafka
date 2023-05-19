package com.thisara.dao.exception;

import com.thisara.exception.ErrorCodes;


public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorCodes errorCode;
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, ErrorCodes errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}
}
