package com.dev_santos.helpdesk.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev_santos.helpdesk.services.exceptions.ExistingObjectException;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ObjectNotFoundException exception, HttpServletRequest httpServletRequest){
		StandardError err = new StandardError(Instant.now(), 
				HttpStatus.NOT_FOUND.value(),
				"Resource not found", exception.getMessage(), 
				httpServletRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ExistingObjectException.class)
 public ResponseEntity<StandardError> existingObject(ExistingObjectException exception, HttpServletRequest httpServletRequest){
	 StandardError err = new StandardError(Instant.now(), 
				HttpStatus.CONFLICT.value(),
				"Existing object", exception.getMessage(), 
				httpServletRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
 }

}
