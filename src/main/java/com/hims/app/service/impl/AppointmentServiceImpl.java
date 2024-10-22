package com.hims.app.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hims.app.dto.Pager;
import com.hims.app.dto.appointment.AppointmentConfirmForm;
import com.hims.app.dto.appointment.AppointmentCreateForm;
import com.hims.app.dto.appointment.AppointmentSummary;
import com.hims.app.dto.appointment.DoctorAppointmentSummary;
import com.hims.app.dto.appointment.PatientAppointmentSummary;
import com.hims.app.model.Appointment;
import com.hims.app.model.field.AppointmentStatus;
import com.hims.app.repository.AppointmentRepository;
import com.hims.app.service.AppointmentService;
import com.hims.app.util.AppointmentUtil;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentUtil appointmentUtil;
	private AppointmentRepository appointmentRepository;
	
	public AppointmentServiceImpl(AppointmentUtil appointmentUtil, AppointmentRepository appointmentRepository) {
		this.appointmentUtil = appointmentUtil;
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public AppointmentSummary createAppointment(AppointmentCreateForm form) {
		Appointment appointment = appointmentUtil.mapToEntity(form);
		appointment = appointmentRepository.save(appointment);
		return appointmentUtil.mapToAppointmentSummary(appointment);
	}


	@Transactional
	@Override
	public AppointmentSummary cancelAppointment(long id) {
		Optional<Appointment> result = appointmentRepository.findById(id);
		if(result.isPresent()) {
			Appointment appointment = result.get();
			appointment.setStatus(AppointmentStatus.CANCELED);
			return appointmentUtil.mapToAppointmentSummary(appointment);
		}
		return null;
	}

	@Transactional
	@Override
	public AppointmentSummary completeAppointment(long id) {
		Optional<Appointment> result = appointmentRepository.findById(id);
		if(result.isPresent()) {
			Appointment appointment = result.get();
			appointment.setStatus(AppointmentStatus.FINISHED);
			return appointmentUtil.mapToAppointmentSummary(appointment);
		}
		return null;
	}

	@Override
	public Pager<AppointmentSummary> getWaitingAppointments(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<AppointmentSummary> page = appointmentRepository.getWaitingAppointmets(AppointmentStatus.WAITING, pageable);
		Pager<AppointmentSummary> pager = new Pager<AppointmentSummary>(page.getContent(), page.getTotalElements(), page.getTotalPages());
		return pager;
	}

	@Override
	public Pager<DoctorAppointmentSummary> getAppointmentsForDoctor(long doctorId, AppointmentStatus status,
			int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<DoctorAppointmentSummary> page = appointmentRepository.getAppointmentSummariesForDoctor(doctorId, status, pageable);
		Pager<DoctorAppointmentSummary> pager = new Pager<DoctorAppointmentSummary>(page.getContent(), page.getNumberOfElements(), page.getTotalPages());
		return pager;
	}

	@Override
	public Pager<PatientAppointmentSummary> getAppointmentsForPatient(long patientId, AppointmentStatus status,
			int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<PatientAppointmentSummary> page = appointmentRepository.getAppointmentSummariesForPatient(patientId, status, pageable);
		Pager<PatientAppointmentSummary> pager = new Pager<PatientAppointmentSummary>(page.getContent(), page.getTotalElements(), page.getTotalPages());
		return pager;
	}


}
