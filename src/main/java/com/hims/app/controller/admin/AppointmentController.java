package com.hims.app.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hims.app.dto.Pager;
import com.hims.app.dto.appointment.AppointmentCreateForm;
import com.hims.app.dto.appointment.AppointmentSummary;
import com.hims.app.dto.appointment.DoctorAppointmentSummary;
import com.hims.app.dto.appointment.PatientAppointmentSummary;
import com.hims.app.model.field.AppointmentStatus;
import com.hims.app.service.AppointmentService;

@RestController
@RequestMapping("/api/${api.version}/admin/appointments")
public class AppointmentController {

	private AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@PostMapping
	public ResponseEntity<AppointmentSummary> createAppointment(@RequestBody AppointmentCreateForm form) {
		AppointmentSummary appointmentSummary = appointmentService.createAppointment(form);
		return ResponseEntity.ok(appointmentSummary);
	}
	
	@GetMapping("/booked/patients/{id}")
	public ResponseEntity<Pager<PatientAppointmentSummary>> getBookedAppointmentsForPatient(@PathVariable long id,
																						   @RequestParam(defaultValue = "0") int pageNumber,
																						   @RequestParam(defaultValue = "10") int pageSize) {
		Pager<PatientAppointmentSummary> pager = appointmentService.getAppointmentsForPatient(id, AppointmentStatus.BOOKED, pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/finished/patients/{id}")
	public ResponseEntity<Pager<PatientAppointmentSummary>> getFinishedAppointmentsForPatient(@PathVariable long id,
																						   @RequestParam(defaultValue = "0") int pageNumber,
																						   @RequestParam(defaultValue = "10") int pageSize) {
		Pager<PatientAppointmentSummary> pager = appointmentService.getAppointmentsForPatient(id, AppointmentStatus.FINISHED, pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/canceled/patients/{id}")
	public ResponseEntity<Pager<PatientAppointmentSummary>> getCanceledAppointmentsForPatient(@PathVariable long id,
																						   @RequestParam(defaultValue = "0") int pageNumber,
																						   @RequestParam(defaultValue = "10") int pageSize) {
		Pager<PatientAppointmentSummary> pager = appointmentService.getAppointmentsForPatient(id, AppointmentStatus.CANCELED, pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	
	@GetMapping("/booked/doctors/{id}")
	public ResponseEntity<Pager<DoctorAppointmentSummary>> getBookedAppointmentsForDoctor(@PathVariable long id,
																						  @RequestParam(defaultValue = "0") int pageNumber,
																						  @RequestParam(defaultValue = "10") int pageSize) {
		Pager<DoctorAppointmentSummary> pager = appointmentService.getAppointmentsForDoctor(id, AppointmentStatus.BOOKED, pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/finished/doctors/{id}")
	public ResponseEntity<Pager<DoctorAppointmentSummary>> getFinishedAppointmentsForDoctor(@PathVariable long id,
																						  @RequestParam(defaultValue = "0") int pageNumber,
																						  @RequestParam(defaultValue = "10") int pageSize) {
		Pager<DoctorAppointmentSummary> pager = appointmentService.getAppointmentsForDoctor(id, AppointmentStatus.FINISHED, pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/canceled/doctors/{id}")
	public ResponseEntity<Pager<DoctorAppointmentSummary>> getCanceledAppointmentsForDoctor(@PathVariable long id,
																						  @RequestParam(defaultValue = "0") int pageNumber,
																						  @RequestParam(defaultValue = "10") int pageSize) {
		Pager<DoctorAppointmentSummary> pager = appointmentService.getAppointmentsForDoctor(id, AppointmentStatus.CANCELED, pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	
}
