package com.hims.app.dto.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AppointmentSummary(long id, String patientFirstName, String patientLastName, 
								String doctorFirstName, String doctorLastName, LocalDateTime requestedDate) {

}
