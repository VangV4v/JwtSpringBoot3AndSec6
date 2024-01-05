package com.vang.securitywithjwt.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandleController {

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ResponseEntity<String> accessDenied(Principal principal) {

		return new ResponseEntity<String>(principal.getName() + " Cannot access it !",HttpStatus.OK);
	}
}