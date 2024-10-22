package com.hims.app.dto.doctor;

import com.hims.app.model.field.DoctorRank;

public record DoctorDepartmentInfo(long id, String firstName, String lastName, DoctorRank rank) {

}
