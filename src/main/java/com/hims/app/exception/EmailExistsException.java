package com.hims.app.exception;

public class EmailExistsException extends RuntimeException {

	private static final long serialVersionUID = -6120528217806676416L;

	public EmailExistsException(String email) {
		super("The email "+email+" already exists");
	}
}
