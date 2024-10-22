package com.hims.app.util.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hims.app.dto.doctor.DoctorDetail;
import com.hims.app.dto.doctor.DoctorRegisterForm;
import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.dto.doctor.DoctorUpdateForm;
import com.hims.app.model.Department;
import com.hims.app.model.Doctor;
import com.hims.app.repository.DepartmentRepository;
import com.hims.app.repository.DoctorRepository;
import com.hims.app.util.DoctorUtil;

@Component
public class DoctorUtilImpl implements DoctorUtil {

	private DepartmentRepository departmentRepository;
	private DoctorRepository doctorRepository;
	
	public DoctorUtilImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository) {
		this.departmentRepository = departmentRepository;
		this.doctorRepository = doctorRepository;
	}

	@Override
	public Doctor mapToEntity(DoctorRegisterForm form) {
		Doctor doctor = new Doctor();
		doctor.setFirstName(form.firstName());
		doctor.setLastName(form.lastName());
		doctor.setEmployedDate(form.employedDate());
		doctor.setEmail(form.email());
		doctor.setPhone(form.phone());
		doctor.setGender(form.gender());
		doctor.setDoctorRank(form.rank());
		doctor.setLicenseNumber(form.licenseNumber());
		doctor.setDepartment(getDepartment(form.departmentId()));
		return doctor;
	}
	
	@Override
	public Doctor mapToEntity(DoctorUpdateForm form) {
		Optional<Doctor> result =  doctorRepository.findById(form.id());
		if(result.isPresent()) {
			Doctor doctor = result.get();
			doctor.setLastName(form.lastName());
			doctor.setEmail(form.email());
			doctor.setPhone(form.phone());
			doctor.setDoctorRank(form.rank());
			return doctor;
		}
		return null;
	}

	@Override
	public DoctorSummary mapToDoctorSummary(Doctor doctor) {
		return new DoctorSummary(doctor.getId(), doctor.getFirstName(), doctor.getLastName(), 
				doctor.getLicenseNumber(), doctor.getDepartment().getName(), doctor.getDoctorRank());
	}
	
	private Department getDepartment(long departmentId) {
		Optional<Department> result = departmentRepository.findById(departmentId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	

	

}
