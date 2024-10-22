package com.hims.app.service;

import java.util.List;

import com.hims.app.dto.doctor.DoctorRegisterForm;
import com.hims.app.dto.doctor.DoctorUpdateForm;
import com.hims.app.dto.Pager;
import com.hims.app.dto.doctor.DoctorDepartmentInfo;
import com.hims.app.dto.doctor.DoctorDetail;
import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.exception.EmailExistsException;
import com.hims.app.exception.LicenseExistsException;
import com.hims.app.exception.PhoneExistsException;

public interface DoctorService {

	DoctorSummary registerDoctor(DoctorRegisterForm form)
			throws EmailExistsException, PhoneExistsException, LicenseExistsException;

	DoctorSummary updateDoctor(DoctorUpdateForm form) throws EmailExistsException, PhoneExistsException;

	void removeDoctor(long id);

	Pager<DoctorSummary> getDoctorList(int pageNumber, int pageSize);

	List<DoctorDepartmentInfo> getDoctorsForBooking(long departmentId);

	DoctorDetail getDoctorDetail(long id);

}
