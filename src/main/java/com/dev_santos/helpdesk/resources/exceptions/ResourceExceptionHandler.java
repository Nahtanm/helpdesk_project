package com.dev_santos.helpdesk.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev_santos.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ObjectNotFoundException exception,
			HttpServletRequest httpServletRequest) {
		StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Resource not found",
				exception.getMessage(), httpServletRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException exception,
			HttpServletRequest httpServletRequest) {
		StandardError err = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Data violation",
				exception.getMessage(), httpServletRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationErrors(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest){
		
		ValidationError errors = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Validation error",
				"Erro na validação dos campos!", httpServletRequest.getRequestURI());
		
		for(FieldError x : exception.getFieldErrors() ) {
			errors.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
	}

}
