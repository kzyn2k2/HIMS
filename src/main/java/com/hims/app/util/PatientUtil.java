package com.hims.app.util;

import com.hims.app.dto.patient.PatientRegisterForm;
import com.hims.app.dto.patient.PatientSummary;
import com.hims.app.dto.patient.PatientUpdateForm;
import com.hims.app.model.Patient;

public interface PatientUtil {

	Patient mapToEntity(PatientRegisterForm form);
	
	Patient mapToEntity(PatientUpdateForm form);
	
	PatientSummary mapToPatientSummary(Patient patient);
	
}
