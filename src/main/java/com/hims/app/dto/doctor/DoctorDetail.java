package com.hims.app.dto.doctor;

import java.time.LocalDate;

import com.hims.app.model.field.DoctorRank;
import com.hims.app.model.field.Gender;

public record DoctorDetail(long id, String firstName, String lastName, String email, String phone,
		DoctorRank rank, String licenseNumber, String departmentName, LocalDate employedDate, boolean active,
		Gender gender) {

}
