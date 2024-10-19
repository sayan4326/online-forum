package com.yourcompanyname.forum.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleFieldException(MethodArgumentNotValidException exception) {
		String errorMessage = "";
		List<FieldError> fieldErrors = exception.getFieldErrors();

		for (FieldError error : fieldErrors)
			errorMessage = errorMessage + error.getDefaultMessage() + "\n";

		return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException exception) {
		return new ResponseEntity<String>(exception.getErrorMessage(), exception.getHttpStatus());
	}
}
