package com.hims.app.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hims.app.dto.Pager;
import com.hims.app.dto.patient.PatientDetail;
import com.hims.app.dto.patient.PatientRegisterForm;
import com.hims.app.dto.patient.PatientSummary;
import com.hims.app.dto.patient.PatientUpdateForm;
import com.hims.app.service.PatientService;

@RestController
@RequestMapping("/api/${api.version}/admin/patients")
public class PatientController {
	
	private PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@PostMapping
	public ResponseEntity<PatientSummary> registerPatient(@RequestBody PatientRegisterForm form) {
		PatientSummary patientSummary = patientService.registerPatient(form);
		return ResponseEntity.ok(patientSummary);
	}
	
	@PatchMapping
	public ResponseEntity<PatientSummary> updatePatient(@RequestBody PatientUpdateForm form) {
		PatientSummary patientSummary = patientService.updatePatient(form);
		return ResponseEntity.ok(patientSummary);
	}
	
	@GetMapping
	public ResponseEntity<Pager<PatientSummary>> getPatientSummaries(@RequestParam(defaultValue = "0") int pageNumber,
																	 @RequestParam(defaultValue = "10") int pageSize) {
		Pager<PatientSummary> pager = patientService.getPatientList(pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientDetail> getPatientDetail(@PathVariable long id) {
		PatientDetail patientDetail = patientService.getPatientDetail(id);
		return ResponseEntity.ok(patientDetail);
	}
	
	
	
	
}
