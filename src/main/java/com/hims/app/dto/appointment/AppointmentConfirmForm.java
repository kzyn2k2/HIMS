package com.hims.app.dto.appointment;

import java.time.LocalDateTime;

public record AppointmentConfirmForm(long id, LocalDateTime appointmentDate) {

}
