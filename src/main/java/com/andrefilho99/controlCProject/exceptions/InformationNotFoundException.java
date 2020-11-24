package com.andrefilho99.controlCProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InformationNotFoundException(String message) {
		super(message);
	}
}
