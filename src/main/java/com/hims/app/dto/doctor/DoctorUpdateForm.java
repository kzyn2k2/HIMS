package com.hims.app.dto.doctor;

import com.hims.app.model.field.DoctorRank;

public record DoctorUpdateForm(long id, String lastName, String email,
		DoctorRank rank, String phone) {

}
