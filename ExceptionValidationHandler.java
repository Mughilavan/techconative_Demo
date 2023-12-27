package com.techconative.demo.exception;

import com.techconative.demo.constants.Constant;
import com.techconative.demo.controller.PostController;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionValidationHandler {

	private Logger logger =  LoggerFactory.getLogger(ExceptionValidationHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.reduce((msg1, msg2) -> msg1 + "\n" + msg2).orElse("Invalid request");
		return ResponseEntity.badRequest().body(errorMessage);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		String errorMsg = "";
		if (ex.getMessage().contains("unique_userName")) {
			errorMsg = Constant.USER_ALREADY_EXIST;
		}
		else if (ex.getMessage().contains("unique_email")) {
			errorMsg = Constant.EMAIL_ALREADY_EXIST;
		}
		else if (ex.getMessage().contains("unique_mobileNumber")) {
			errorMsg = Constant.MOBILE_NUMBER_ALREADY_EXIST;
		}
		else if (ex.getMessage().contains("unique_aadharNumber")) {
			errorMsg = Constant.AADHAR_NUMBER_ALREADY_EXIST;
		}
		
		return ResponseEntity.badRequest().body(errorMsg);
	}
}
