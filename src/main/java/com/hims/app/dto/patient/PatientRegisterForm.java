package com.hims.app.dto.patient;

import java.io.Serializable;
import java.time.LocalDate;

import com.hims.app.model.field.Gender;

public record PatientRegisterForm(String firstName,
									 String lastName,
									 LocalDate dateOfBirth,
									 Gender gender,
									 String phone,
									 String email, 
									 String address) implements Serializable {

	
	
}
