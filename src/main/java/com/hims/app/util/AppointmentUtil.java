package com.hims.app.util;

import com.hims.app.dto.appointment.AppointmentCreateForm;
import com.hims.app.dto.appointment.AppointmentSummary;
import com.hims.app.model.Appointment;

public interface AppointmentUtil {

	Appointment mapToEntity(AppointmentCreateForm form);
	
	AppointmentSummary mapToAppointmentSummary(Appointment appointment);
}
