package com.hims.app.service;

import com.hims.app.dto.nurse.NurseRegisterForm;
import com.hims.app.dto.nurse.NurseUpdateForm;
import com.hims.app.dto.Pager;
import com.hims.app.dto.nurse.NurseDetail;
import com.hims.app.dto.nurse.NurseSummary;
import com.hims.app.exception.EmailExistsException;
import com.hims.app.exception.LicenseExistsException;
import com.hims.app.exception.PhoneExistsException;

public interface NurseService {

	NurseSummary registerNurse(NurseRegisterForm form) throws EmailExistsException, PhoneExistsException, LicenseExistsException;
	
	NurseSummary updateNurse(NurseUpdateForm form) throws EmailExistsException, PhoneExistsException, LicenseExistsException;
	
	void removeNurse(long id);
	
	Pager<NurseSummary> getNurseList(int pageNumber, int pageSize);
	
	NurseDetail getNurseDetail(long id);
	
}
