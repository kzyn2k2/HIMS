package com.hims.app.dto.nurse;

import com.hims.app.model.field.NurseRank;

public record NurseDepartmentInfo(long id,
										String firstName,
										String lastName,
										NurseRank rank) {

}
