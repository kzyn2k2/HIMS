package com.hims.app.dto.nurse;

import com.hims.app.model.field.NurseRank;

public record NurseUpdateForm(
			long id,
		   String firstName,
		   String lastName,
		   String email,
		   String phone,
		   	NurseRank rank,
		   	boolean active) {

}
