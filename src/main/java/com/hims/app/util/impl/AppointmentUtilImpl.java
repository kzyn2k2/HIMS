package com.hims.app.util.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.hims.app.dto.appointment.AppointmentCreateForm;
import com.hims.app.dto.appointment.AppointmentSummary;
import com.hims.app.model.Appointment;
import com.hims.app.model.Doctor;
import com.hims.app.model.Patient;
import com.hims.app.repository.DoctorRepository;
import com.hims.app.repository.PatientRepository;
import com.hims.app.util.AppointmentUtil;

@Component
public class AppointmentUtilImpl implements AppointmentUtil {

	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;
	
	public AppointmentUtilImpl(DoctorRepository doctorRepository, PatientRepository patientRepository) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public Appointment mapToEntity(AppointmentCreateForm form) {
		return Appointment.builder().appointmentDate(form.appointmentDate()).patient(getPatient(form.patientId())).doctor(getDoctor(form.doctorId())).build();
	}

	@Override
	public AppointmentSummary mapToAppointmentSummary(Appointment appointment) {
		Patient patient = appointment.getPatient();
		Doctor doctor = appointment.getDoctor();
		return new AppointmentSummary(appointment.getId(), patient.getFirstName(), 
				patient.getLastName(), doctor.getFirstName(), doctor.getLastName(), appointment.getAppointmentDate());
	}
	
	private Patient getPatient(long id) {
		Optional<Patient> result = patientRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	private Doctor getDoctor(long id) {
		Optional<Doctor> result = doctorRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

}
