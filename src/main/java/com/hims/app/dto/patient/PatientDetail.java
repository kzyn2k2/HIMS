package com.hims.app.dto.patient;

import java.time.LocalDate;

import com.hims.app.model.field.Gender;

public record PatientDetail(long id,
							   String firstName,
							   String lastName,
							   LocalDate dateOfBirth,
							   String email, 
							   String phone, 
							   String address,
							   	Gender gender) {

}
