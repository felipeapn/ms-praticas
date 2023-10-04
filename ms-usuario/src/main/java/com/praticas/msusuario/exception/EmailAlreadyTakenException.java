package com.praticas.msusuario.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyTakenException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
	
	public EmailAlreadyTakenException() {
		super.setHttpStatusCode(HttpStatus.BAD_REQUEST);
		super.setDescription("Email ya utilizado");
		super.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
	}

}
