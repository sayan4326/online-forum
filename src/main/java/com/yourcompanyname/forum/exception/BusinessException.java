package com.yourcompanyname.forum.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	private HttpStatus httpStatus;

	public BusinessException(String errorMessage, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
