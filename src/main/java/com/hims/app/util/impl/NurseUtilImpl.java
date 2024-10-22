package com.hims.app.util.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.dto.nurse.NurseRegisterForm;
import com.hims.app.dto.nurse.NurseSummary;
import com.hims.app.dto.nurse.NurseUpdateForm;
import com.hims.app.model.Department;
import com.hims.app.model.Doctor;
import com.hims.app.model.Nurse;
import com.hims.app.repository.DepartmentRepository;
import com.hims.app.repository.NurseRepository;
import com.hims.app.util.NurseUtil;

@Component
public class NurseUtilImpl implements NurseUtil {

	private NurseRepository nurseRepository;
	private DepartmentRepository departmentRepository;
	
	public NurseUtilImpl(NurseRepository nurseRepository, DepartmentRepository departmentRepository) {
		this.nurseRepository = nurseRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Nurse mapToEntity(NurseRegisterForm form) {
		Nurse nurse = new Nurse();
		nurse.setFirstName(form.firstName());
		nurse.setLastName(form.lastName());
		nurse.setEmployedDate(form.employedDate());
		nurse.setEmail(form.email());
		nurse.setPhone(form.phone());
		nurse.setGender(form.gender());
		nurse.setNurseRank(form.rank());
		nurse.setLicenseNumber(form.licenseNumber());
		nurse.setDepartment(getDepartment(form.departmentId()));
		return nurse;
	}

	@Override
	public Nurse mapToEntity(NurseUpdateForm form) {
		Optional<Nurse> result =  nurseRepository.findById(form.id());
		if(result.isPresent()) {
			Nurse nurse = result.get();
			nurse.setLastName(form.lastName());
			nurse.setEmail(form.email());
			nurse.setPhone(form.phone());
			nurse.setNurseRank(form.rank());
			return nurse;
		}
		return null;
	}

	@Override
	public NurseSummary mapToNurseSummary(Nurse nurse) {
		return new NurseSummary(nurse.getId(), nurse.getFirstName(), nurse.getLastName(), 
				nurse.getLicenseNumber(), nurse.getDepartment().getName(), nurse.getNurseRank());
	}
	
	private Department getDepartment(long departmentId) {
		Optional<Department> result = departmentRepository.findById(departmentId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

}
