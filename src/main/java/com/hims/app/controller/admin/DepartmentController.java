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

import com.hims.app.dto.Pager;
import com.hims.app.dto.department.DepartmentDetail;
import com.hims.app.dto.department.DepartmentOption;
import com.hims.app.dto.department.DepartmentRegisterForm;
import com.hims.app.dto.department.DepartmentSummary;
import com.hims.app.dto.department.DepartmentUpdateForm;
import com.hims.app.service.DepartmentService;


@RestController
@RequestMapping("/api/${api.version}/admin/departments")
public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping
	public ResponseEntity<Pager<DepartmentSummary>> getDepartmentList(@RequestParam(defaultValue = "0") int pageNumber,
																	  @RequestParam(defaultValue = "10") int pageSize) {
		Pager<DepartmentSummary> pager = departmentService.getDepartmentList(pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDetail> getDepartmentDetail(@PathVariable long id) {
		DepartmentDetail departmentDetail = departmentService.getDepartmentDetail(id);
		return ResponseEntity.ok(departmentDetail);
	}
	
	@GetMapping("/options")
	public ResponseEntity<List<DepartmentOption>> getDepartmentOptions() {
		List<DepartmentOption> departmentOptions = departmentService.getDepartmentOptions();
		return ResponseEntity.ok(departmentOptions);
	}
	
	@GetMapping("/bookings")
	public ResponseEntity<List<DepartmentSummary>> getDepartmentsForBooking() {
		List<DepartmentSummary> departmentSummaries = departmentService.getDepartmentsForPatient();
		return ResponseEntity.ok(departmentSummaries);
	}
	
	@PostMapping
	public ResponseEntity<DepartmentSummary> registerDepartment(@RequestBody DepartmentRegisterForm form) {
		DepartmentSummary departmentSummary = departmentService.registerDepartment(form);
		return ResponseEntity.ok(departmentSummary);
	}
	
	@PatchMapping
	public ResponseEntity<DepartmentSummary> updateDepartment(@RequestBody DepartmentUpdateForm form) {
		DepartmentSummary departmentSummary = departmentService.updateDepartment(form);
		return ResponseEntity.ok(departmentSummary);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> removeDepartment(@PathVariable long id) {
		departmentService.removeDepartment(id);
		return ResponseEntity.ok(Map.of("Status", "Successfully deleted"));
	}
	
}
