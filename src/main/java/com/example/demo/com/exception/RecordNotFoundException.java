package com.example.demo.com.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecordNotFoundException  extends Exception{

	public RecordNotFoundException() {
		
	}
	public RecordNotFoundException(String message) {
		super(message);
	}
}

