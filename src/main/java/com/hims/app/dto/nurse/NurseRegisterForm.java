package com.hims.app.dto.nurse;

import java.time.LocalDate;

import com.hims.app.model.field.Gender;
import com.hims.app.model.field.NurseRank;

public record NurseRegisterForm(String firstName,
								   String lastName,
								   LocalDate employedDate,
								   String licenseNumber,
								   String email,
								   String phone,
								   Gender gender,
								   	long departmentId,
								   	NurseRank rank) {

}
