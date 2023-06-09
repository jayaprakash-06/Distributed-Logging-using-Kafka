package com.thisara.exception.response;


public class ValidationError {

	private String code;
	private String defaultMessage;
	private String field;
	private String rejectedValue;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDefaultMessage() {
		return defaultMessage;
	}
	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getRejectedValue() {
		return rejectedValue;
	}
	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}
}
