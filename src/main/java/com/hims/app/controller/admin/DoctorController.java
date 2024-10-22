package com.hims.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hims.app.service.DoctorService;
import com.hims.app.dto.Pager;
import com.hims.app.dto.doctor.DoctorDepartmentInfo;
import com.hims.app.dto.doctor.DoctorDetail;
import com.hims.app.dto.doctor.DoctorRegisterForm;
import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.dto.doctor.DoctorUpdateForm;

@RestController
@RequestMapping("/api/${api.version}/admin/doctors")
public class DoctorController {

	private DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@PostMapping
	public ResponseEntity<DoctorSummary> registerDoctor(@RequestBody DoctorRegisterForm dto) {
		DoctorSummary doctorSummary = doctorService.registerDoctor(dto);
		return ResponseEntity.ok(doctorSummary);
	}
	
	@PatchMapping
	public ResponseEntity<DoctorSummary> updateDoctor(@RequestBody DoctorUpdateForm dto) {
		DoctorSummary doctorSummary = doctorService.updateDoctor(dto);
		return ResponseEntity.ok(doctorSummary);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> removeDoctor(@PathVariable long id) {
		doctorService.removeDoctor(id);
		return ResponseEntity.ok(Map.of("Status", "Successfully deleted"));
	}
	
	@GetMapping
	public ResponseEntity<Pager<DoctorSummary>> getDoctorSummaries(@RequestParam(defaultValue = "0") int pageNumber,
														@RequestParam(defaultValue = "10") int pageSize) {
		Pager<DoctorSummary> pager = doctorService.getDoctorList(pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DoctorDetail> getDoctorDetail(@PathVariable long id) {
		DoctorDetail doctorDetail = doctorService.getDoctorDetail(id);
		return ResponseEntity.ok(doctorDetail);
	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<List<DoctorDepartmentInfo>> getDoctorsForBooking(@PathVariable long id) {
		List<DoctorDepartmentInfo> doctors = doctorService.getDoctorsForBooking(id);
		return ResponseEntity.ok(doctors);
	}
	
	
	
}
