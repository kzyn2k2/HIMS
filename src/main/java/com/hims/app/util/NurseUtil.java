package com.hims.app.util;

import com.hims.app.dto.nurse.NurseRegisterForm;
import com.hims.app.dto.nurse.NurseSummary;
import com.hims.app.dto.nurse.NurseUpdateForm;
import com.hims.app.model.Nurse;

public interface NurseUtil {

	Nurse mapToEntity(NurseRegisterForm form);
	
	Nurse mapToEntity(NurseUpdateForm form);
	
	NurseSummary mapToNurseSummary(Nurse nurse);
}
