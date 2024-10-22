package com.hims.app.util.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hims.app.dto.patient.PatientRegisterForm;
import com.hims.app.dto.patient.PatientSummary;
import com.hims.app.dto.patient.PatientUpdateForm;
import com.hims.app.model.Patient;
import com.hims.app.repository.PatientRepository;
import com.hims.app.util.PatientUtil;

@Component
public class PatientUtilImpl implements PatientUtil {

	private PatientRepository patientRepository;
	
	public PatientUtilImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient mapToEntity(PatientRegisterForm form) {
		return Patient.builder().firstName(form.firstName()).lastName(form.lastName())
				.dateOfBirth(form.dateOfBirth()).gender(form.gender()).phone(form.phone()).email(form.email()).address(form.address()).build();
	}

	@Override
	public PatientSummary mapToPatientSummary(Patient patient) {
		return new PatientSummary(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getGender(), patient.getEmail(), patient.getPhone());
	}

	@Override
	public Patient mapToEntity(PatientUpdateForm form) {
		Optional<Patient> result = patientRepository.findById(form.id());
		if(result.isPresent()) {
			Patient patient = result.get();
			patient.setLastName(form.lastName());
			patient.setEmail(form.email());
			patient.setPhone(form.phone());
			patient.setAddress(form.address());
			return patient;
		}
		return null;
	}

}
