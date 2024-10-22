package com.hims.app.util;

import com.hims.app.dto.doctor.DoctorRegisterForm;
import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.dto.doctor.DoctorUpdateForm;
import com.hims.app.model.Doctor;

public interface DoctorUtil {

	Doctor mapToEntity(DoctorRegisterForm form);
	
	Doctor mapToEntity(DoctorUpdateForm form);
	
	DoctorSummary mapToDoctorSummary(Doctor doctor);
	
}
