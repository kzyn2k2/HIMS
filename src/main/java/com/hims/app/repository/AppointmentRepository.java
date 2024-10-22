package com.hims.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hims.app.model.Appointment;
import com.hims.app.dto.appointment.AppointmentSummary;
import com.hims.app.dto.appointment.DoctorAppointmentSummary;
import com.hims.app.dto.appointment.PatientAppointmentSummary;
import com.hims.app.model.field.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query("SELECT new com.hims.app.dto.appointment.DoctorAppointmentSummary(a.id, a.patient.firstName, "
			+ "a.patient.lastName, a.appointmentDate) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status = :status")
	Page<DoctorAppointmentSummary> getAppointmentSummariesForDoctor(@Param("doctorId") long doctorId,
																	@Param("status") AppointmentStatus status,
																	Pageable pageable);
	@Query("SELECT new com.hims.app.dto.appointment.PatientAppointmentSummary(a.id, a.doctor.firstName, "
			+ "a.doctor.lastName, a.appointmentDate) FROM Appointment a WHERE a.patient.id = :patientId AND a.status = :status")
	Page<PatientAppointmentSummary> getAppointmentSummariesForPatient(@Param("patientId") long patientId,
																	@Param("status") AppointmentStatus status,
																	Pageable pageable);
	@Query("SELECT new com.hims.app.dto.appointment.AppointmentSummary(a.id, a.patient.firstName, a.patient.lastName, a.doctor.firstName,"
			+ " a.doctor.lastName, a.appointmentDate) FROM Appointment a WHERE a.status = :status")
	Page<AppointmentSummary> getWaitingAppointmets(@Param("status") AppointmentStatus status, Pageable pageable);
	
	@Query("SELECT new com.hims.app.dto.appointment.PatientAppointmentSummary(a.id,a.doctor.firstName,"
			+ "a.doctor.lastName, a.appointmentDate) FROM Appointment a WHERE a.id = :appointmentId AND a.status = :status")
	PatientAppointmentSummary getAppointmentPatient(@Param("appointmentId") long id, @Param("status") AppointmentStatus status);
	
	@Query("SELECT new com.hims.app.dto.appointment.DoctorAppointmentSummary(a.id, a.patient.firstName, "
			+ "a.patient.lastName, a.appointmentDate) FROM Appointment a WHERE a.id = :appointmentId AND a.status = :status")
	DoctorAppointmentSummary getAppointmentDoctor(@Param("appointmentId") long id, @Param("status") AppointmentStatus status);
	
}
