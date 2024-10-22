package com.hims.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hims.app.dto.Pager;
import com.hims.app.dto.nurse.NurseDetail;
import com.hims.app.dto.nurse.NurseRegisterForm;
import com.hims.app.dto.nurse.NurseSummary;
import com.hims.app.dto.nurse.NurseUpdateForm;
import com.hims.app.exception.EmailExistsException;
import com.hims.app.exception.LicenseExistsException;
import com.hims.app.exception.PhoneExistsException;
import com.hims.app.model.Nurse;
import com.hims.app.repository.NurseRepository;
import com.hims.app.service.NurseService;
import com.hims.app.util.NurseUtil;
import com.hims.app.dto.employee.RegisterInfoCheckDTO;
import com.hims.app.dto.employee.UpdateInfoCheckDTO;

@Service
public class NurseServiceImpl implements NurseService {

	private NurseUtil nurseUtil;
	private NurseRepository nurseRepository;
	
	public NurseServiceImpl(NurseUtil nurseUtil, NurseRepository nurseRepository) {
		this.nurseUtil = nurseUtil;
		this.nurseRepository = nurseRepository;
	}

	@Override
	public NurseSummary registerNurse(NurseRegisterForm form)
			throws EmailExistsException, PhoneExistsException, LicenseExistsException {
		checkRegisterInfoExists(form.email(), form.phone(), form.licenseNumber());
		Nurse nurse = nurseUtil.mapToEntity(form);
		nurse = nurseRepository.save(nurse);
		return nurseUtil.mapToNurseSummary(nurse);
	}

	@Override
	public NurseSummary updateNurse(NurseUpdateForm form)
			throws EmailExistsException, PhoneExistsException, LicenseExistsException {
		checkUpdateInfoExists(form.id(), form.email(), form.phone());
		Nurse nurse = nurseUtil.mapToEntity(form);
		nurse = nurseRepository.save(nurse);
		return nurseUtil.mapToNurseSummary(nurse);
	}

	@Override
	public void removeNurse(long id) {
		nurseRepository.removeNures(id);
	}

	@Override
	public Pager<NurseSummary> getNurseList(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<NurseSummary> page = nurseRepository.getNurseSummaries(pageable);
		Pager<NurseSummary> pager = new Pager<NurseSummary>(page.getContent(), page.getTotalElements(), page.getTotalPages());
		return pager;
	}


	@Override
	public NurseDetail getNurseDetail(long id) {
		return nurseRepository.getNurseDetail(id);
	}
	
	private void checkRegisterInfoExists(String email, String phone, String licenseNumber)
			throws EmailExistsException, PhoneExistsException, LicenseExistsException {
		RegisterInfoCheckDTO info = nurseRepository.checkRegisterInfoExists(email, phone, licenseNumber);
		if (info != null && info.email() != null)
			throw new EmailExistsException(email);
		if (info != null && info.phone() != null)
			throw new PhoneExistsException(phone);
		if (info != null && info.licenseNumber() != null)
			throw new LicenseExistsException(licenseNumber);
	}

	private void checkUpdateInfoExists(long id, String email, String phone) throws EmailExistsException, PhoneExistsException {
		UpdateInfoCheckDTO info = nurseRepository.checkUpdateInfoExists(id, email, phone);
		if (info != null && info.email() != null)
			throw new EmailExistsException(email);
		if (info != null && info.phone() != null)
			throw new PhoneExistsException(phone);
	}


}
