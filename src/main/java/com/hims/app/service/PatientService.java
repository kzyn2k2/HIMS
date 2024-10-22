package com.hims.app.service;

import com.hims.app.dto.Pager;
import com.hims.app.dto.patient.PatientDetail;
import com.hims.app.dto.patient.PatientRegisterForm;
import com.hims.app.dto.patient.PatientSummary;
import com.hims.app.dto.patient.PatientUpdateForm;
import com.hims.app.exception.EmailExistsException;
import com.hims.app.exception.PhoneExistsException;

public interface PatientService {

	PatientSummary registerPatient(PatientRegisterForm form) throws EmailExistsException, PhoneExistsException;
	
	PatientSummary updatePatient(PatientUpdateForm form) throws EmailExistsException, PhoneExistsException;
	
	Pager<PatientSummary> getPatientList(int pageNumber, int pageSize);
	
	PatientDetail getPatientDetail(long id);
	
	Pager<PatientSummary> searchPatientByKeyword(String keyword);
	
}
