package com.hims.app.service;

import com.hims.app.dto.Pager;
import com.hims.app.dto.appointment.AppointmentConfirmForm;
import com.hims.app.dto.appointment.AppointmentCreateForm;
import com.hims.app.dto.appointment.AppointmentSummary;
import com.hims.app.dto.appointment.DoctorAppointmentSummary;
import com.hims.app.dto.appointment.PatientAppointmentSummary;
import com.hims.app.model.field.AppointmentStatus;

public interface AppointmentService {

	AppointmentSummary createAppointment(AppointmentCreateForm dto);
	
	AppointmentSummary cancelAppointment(long id);
	
	AppointmentSummary completeAppointment(long id);
			
	Pager<AppointmentSummary> getWaitingAppointments(int pageNumber, int pageSize);
	
	Pager<DoctorAppointmentSummary> getAppointmentsForDoctor(long doctorId, AppointmentStatus status, int pageNumber, int pageSize);
	
	Pager<PatientAppointmentSummary> getAppointmentsForPatient(long patientId, AppointmentStatus status, int pageNumber, int pageSize);

}
