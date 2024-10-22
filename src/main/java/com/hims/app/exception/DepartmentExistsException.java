package com.hims.app.exception;

public class DepartmentExistsException extends RuntimeException {

	private static final long serialVersionUID = -6418291784262529345L;

	public DepartmentExistsException(String name) {
		super("The "+name+" department already exists");
	}
}
