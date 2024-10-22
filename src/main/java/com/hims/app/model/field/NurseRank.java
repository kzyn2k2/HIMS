package com.hims.app.model.field;

import org.springframework.security.core.GrantedAuthority;

public enum NurseRank implements GrantedAuthority {

	CHIEF, SUPERVISOR, ASSISTANT, INTERN;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.name();
	}
}
