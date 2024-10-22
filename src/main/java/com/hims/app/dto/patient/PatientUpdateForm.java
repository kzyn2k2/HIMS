package com.hims.app.dto.patient;

public record PatientUpdateForm(
		 long id,
		 String lastName,
		 String phone,
		 String email, 
		 String address) {

}
