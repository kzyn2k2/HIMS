package com.hims.app.exception;

public class PhoneExistsException extends RuntimeException{

	private static final long serialVersionUID = -9090157117559374399L;

	public PhoneExistsException(String phone) {
		super("The phone "+phone+" already exists");
	}
}
