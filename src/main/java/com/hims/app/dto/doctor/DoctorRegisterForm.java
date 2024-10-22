package com.hims.app.dto.doctor;

import java.time.LocalDate;

import com.hims.app.model.field.DoctorRank;
import com.hims.app.model.field.Gender;

public record DoctorRegisterForm(String firstName, String lastName, LocalDate employedDate, String email, String phone,
		Gender gender, DoctorRank rank, String licenseNumber, long departmentId) {

}
