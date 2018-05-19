package com.nelioalves.cursomcs.resources.exceptions;

import java.time.Instant;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.cursomcs.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomcs.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResorceExceptionHandler {

	@ExceptionHandler(value =ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandartError error = new StandartError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(value = DataIntegrityException.class)
	
	public ResponseEntity<StandartError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandartError error = new StandartError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
