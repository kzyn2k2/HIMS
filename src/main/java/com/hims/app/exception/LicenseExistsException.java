package com.hims.app.exception;

public class LicenseExistsException extends RuntimeException {

	private static final long serialVersionUID = -1273125294363476617L;

	public LicenseExistsException(String licenseNumber) {
		super("The license number "+licenseNumber+" exists");
	}
}
