package com.hims.app.dto.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AppointmentCreateForm(long patientId,
								   long doctorId,
								   LocalDateTime appointmentDate) {

}
