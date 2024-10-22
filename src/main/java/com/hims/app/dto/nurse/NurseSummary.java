package com.hims.app.dto.nurse;

import com.hims.app.model.field.NurseRank;

public record NurseSummary(long id,
								   String firstName,
								   String lastName,
								   String licenseNumber,
								   String departmentName,
								   NurseRank rank) {
	
}
