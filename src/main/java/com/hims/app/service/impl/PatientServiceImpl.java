package com.hims.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hims.app.dto.Pager;
import com.hims.app.dto.patient.PatientDetail;
import com.hims.app.dto.patient.PatientRegisterForm;
import com.hims.app.dto.patient.PatientSummary;
import com.hims.app.dto.patient.PatientUpdateForm;
import com.hims.app.exception.EmailExistsException;
import com.hims.app.exception.PhoneExistsException;
import com.hims.app.model.Patient;
import com.hims.app.repository.PatientRepository;
import com.hims.app.service.PatientService;
import com.hims.app.util.PatientUtil;

@Service
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	private PatientUtil patientUtil;
	
	public PatientServiceImpl(PatientRepository patientRepository, PatientUtil patientUtil) {
		this.patientRepository = patientRepository;
		this.patientUtil = patientUtil;
	}

	@Override
	public PatientSummary registerPatient(PatientRegisterForm form) throws EmailExistsException, PhoneExistsException {
		Patient patient = patientUtil.mapToEntity(form);
		patient = patientRepository.save(patient);
		return patientUtil.mapToPatientSummary(patient);
	}

	@Override
	public PatientSummary updatePatient(PatientUpdateForm form) throws EmailExistsException, PhoneExistsException {
		Patient patient = patientUtil.mapToEntity(form);
		patient = patientRepository.save(patient);
		return patientUtil.mapToPatientSummary(patient);
	}

	@Override
	public Pager<PatientSummary> getPatientList(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<PatientSummary> page = patientRepository.getPatientSummaries(pageable);
		Pager<PatientSummary> pager = new Pager<PatientSummary>(page.getContent(), page.getTotalElements(), page.getTotalPages());
		return pager;
	}

	@Override
	public PatientDetail getPatientDetail(long id) {
		return patientRepository.getPatientDetail(id);
	}

	@Override
	public Pager<PatientSummary> searchPatientByKeyword(String keyword) {
		return null;
	}

}
