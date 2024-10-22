package com.hims.app.dto.nurse;

import java.time.LocalDate;

import com.hims.app.model.field.Gender;
import com.hims.app.model.field.NurseRank;

public record NurseDetail(long id,
								   String firstName,
								   String lastName,
								   LocalDate employedDate,
								   String phone,
								   String email,
								   String departmentName,
								   Gender gender,
								   String licenseNumber,
								   boolean active,
								   NurseRank rank) {

}
