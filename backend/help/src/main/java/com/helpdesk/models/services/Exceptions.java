package com.helpdesk.models.services;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exceptions {
	
	
	// https://stackoverflow.com/questions/62896233/how-to-throw-custom-exception-in-proper-way-when-using-javax-validation-valid
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> test(ConstraintViolationException violation) {
		return ResponseEntity.badRequest().body("{'error':'cpf invalido'}");
	}
}
