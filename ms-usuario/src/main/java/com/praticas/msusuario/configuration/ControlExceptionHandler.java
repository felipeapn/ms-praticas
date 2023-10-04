package com.praticas.msusuario.configuration;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.praticas.msusuario.exception.BusinessException;

@RestControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String MS_TRACEID = "ms-usuario";
	private static final String MS_TRACEID_HEY = "Trace-id";
	
	public static final String CONSTRAINT_VALIDATION_FAILED = "Constraint validation failed";
	
	@ExceptionHandler(value = { BusinessException.class})
	protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(MS_TRACEID_HEY, MS_TRACEID);
		
		return ResponseEntity.status(ex.getHttpStatusCode()).headers(httpHeaders).body(ex.getOnlyBody());
	}


	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException (NoSuchElementException ex, WebRequest req) {
		
		BusinessException businessException = BusinessException.builder()
			.httpStatusCode(HttpStatus.BAD_REQUEST)
			.message("Object not found")
			.description(ex.getMessage())
			.uiDescription(ex.getLocalizedMessage())
			.build();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.set(MS_TRACEID_HEY, MS_TRACEID);
		
		return ResponseEntity.status(businessException.getHttpStatusCode())
				.headers(httpHeaders).body(businessException.getOnlyBody());
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        
        BusinessException businessException = BusinessException.builder()
    			.httpStatusCode(HttpStatus.BAD_REQUEST)
    			.message(CONSTRAINT_VALIDATION_FAILED)
    			.description(errorList.toString())
    			.uiDescription("Alguno parametro de la requesicion esta incorrecto")
    			.build();
        
        headers.set(MS_TRACEID_HEY, MS_TRACEID);
        
        return ResponseEntity.status(businessException.getHttpStatusCode())
				.headers(headers).body(businessException.getOnlyBody());
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException (EmptyResultDataAccessException ex, WebRequest req) {
		
		BusinessException businessException = BusinessException.builder()
			.httpStatusCode(HttpStatus.BAD_REQUEST)
			.message("Object not found")
			.description(ex.getMessage())
			.uiDescription(ex.getLocalizedMessage())
			.build();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.set(MS_TRACEID_HEY, MS_TRACEID);
		
		return ResponseEntity.status(businessException.getHttpStatusCode())
				.headers(httpHeaders).body(businessException.getOnlyBody());
	}
	

}
