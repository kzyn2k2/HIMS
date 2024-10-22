package com.hims.app.model.field;

import org.springframework.security.core.GrantedAuthority;

public enum DoctorRank implements GrantedAuthority {

	MEDICAL_DIRECTOR, DEPARTMENT_HEAD, ATTENDING_PHYSICIAN, FELLOW, 
	CHIEF_RESIDENT, SENIOR_RESIDENT, JUNIOR_RESIDENT, INTERN;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.name();
	}
}
