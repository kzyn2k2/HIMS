package com.hims.app.dto.doctor;

import com.hims.app.model.field.DoctorRank;

public record DoctorSummary(long id, String firstName, String lastName, String licenseNumber, String departmentName,
		DoctorRank rank) {

}
