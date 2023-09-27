package com.praticas.msusuario.configuration;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.praticas.msusuario.exception.BusinessException;

@ControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String MS_TRACEID = "ms-usuario";

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException (NoSuchElementException ex, WebRequest req) {
		
		BusinessException businessException = BusinessException.builder()
			.httpStatusCode(HttpStatus.BAD_REQUEST)
			.message("Object not found")
			.description(ex.getMessage())
			.uiDescription(ex.getLocalizedMessage())
			.build();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(MS_TRACEID, this.MS_TRACEID);
		
		return ResponseEntity.status(businessException.getHttpStatusCode())
				.headers(httpHeaders).body(businessException.getOnlyBody());
	}

}
