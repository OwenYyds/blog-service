package com.owen.exception;

import com.owen.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

	Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

	@ExceptionHandler({ Exception.class })
	public ResponseMessage<String> handleException(Exception e) {
		log.error("Exception: ", e);
		return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
	}
}
