package com.hims.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hims.app.dto.Pager;
import com.hims.app.dto.doctor.DoctorDepartmentInfo;
import com.hims.app.dto.doctor.DoctorDetail;
import com.hims.app.dto.doctor.DoctorRegisterForm;
import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.dto.doctor.DoctorUpdateForm;
import com.hims.app.exception.EmailExistsException;
import com.hims.app.exception.LicenseExistsException;
import com.hims.app.exception.PhoneExistsException;
import com.hims.app.model.Doctor;
import com.hims.app.model.field.DoctorRank;
import com.hims.app.repository.DoctorRepository;
import com.hims.app.service.DoctorService;
import com.hims.app.util.DoctorUtil;
import com.hims.app.dto.employee.RegisterInfoCheckDTO;
import com.hims.app.dto.employee.UpdateInfoCheckDTO;

@Service
public class DoctorServiceImpl implements DoctorService {

	private DoctorRepository doctorRepository;
	private DoctorUtil doctorUtil;

	public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorUtil doctorUtil) {
		this.doctorRepository = doctorRepository;
		this.doctorUtil = doctorUtil;
	}

	@Override
	public DoctorSummary registerDoctor(DoctorRegisterForm form)
			throws EmailExistsException, PhoneExistsException, LicenseExistsException {
		checkRegisterInfoExists(form.email(), form.phone(), form.licenseNumber());
		Doctor doctor = doctorUtil.mapToEntity(form);
		doctor = doctorRepository.save(doctor);
		return doctorUtil.mapToDoctorSummary(doctor);
	}

	@Override
	public DoctorSummary updateDoctor(DoctorUpdateForm form) throws EmailExistsException, PhoneExistsException {
		checkUpdateInfoExists(form.id(), form.email(), form.phone());
		Doctor doctor = doctorUtil.mapToEntity(form);
		doctor = doctorRepository.save(doctor);
		return doctorUtil.mapToDoctorSummary(doctor);
	}

	@Override
	public void removeDoctor(long id) {
		doctorRepository.removeDoctor(id);
	}

	@Override
	public Pager<DoctorSummary> getDoctorList(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<DoctorSummary> page = doctorRepository.getDoctorSummaries(pageable);
		return new Pager<DoctorSummary>(page.getContent(), page.getTotalElements(), page.getTotalPages());
	}

	@Override
	public List<DoctorDepartmentInfo> getDoctorsForBooking(long departmentId) {
		return doctorRepository.getDoctorsForBooking(departmentId,
				List.of(DoctorRank.ATTENDING_PHYSICIAN, DoctorRank.DEPARTMENT_HEAD));
	}

	@Override
	public DoctorDetail getDoctorDetail(long id) {
		return doctorRepository.getDoctorDetail(id);
	}

	private void checkRegisterInfoExists(String email, String phone, String licenseNumber)
			throws EmailExistsException, PhoneExistsException, LicenseExistsException {
		RegisterInfoCheckDTO info = doctorRepository.checkRegisterInfoExists(email, phone, licenseNumber);
		if (info != null && info.email() != null)
			throw new EmailExistsException(email);
		if (info != null && info.phone() != null)
			throw new PhoneExistsException(phone);
		if (info != null && info.licenseNumber() != null)
			throw new LicenseExistsException(licenseNumber);
	}

	private void checkUpdateInfoExists(long id, String email, String phone)
			throws EmailExistsException, PhoneExistsException {
		UpdateInfoCheckDTO info = doctorRepository.checkUpdateInfoExists(id, email, phone);
		if (info != null && info.email() != null)
			throw new EmailExistsException(email);
		if (info != null && info.phone() != null)
			throw new PhoneExistsException(phone);
	}

}
