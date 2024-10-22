package com.hims.app.dto.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PatientAppointmentSummary(long id,
							            String doctorFirstName,
										String doctorLastName,
										LocalDateTime appointmentDate) {

}
