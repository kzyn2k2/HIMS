package com.hims.app.dto.patient;

import java.io.Serializable;

import com.hims.app.model.field.Gender;

public record PatientSummary(long id,
							  String firstName,
							  String lastName,
							  Gender gender,
							  String email,
							  String phone) implements Serializable {

}
