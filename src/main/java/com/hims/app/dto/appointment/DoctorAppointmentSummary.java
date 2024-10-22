package com.hims.app.dto.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DoctorAppointmentSummary(long id, String patientFirstName, String patientLastName, LocalDateTime appointmentDate) {

}
