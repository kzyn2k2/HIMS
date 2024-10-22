package com.hims.app.controller.admin;

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
import com.hims.app.dto.nurse.NurseDetail;
import com.hims.app.dto.nurse.NurseRegisterForm;
import com.hims.app.dto.nurse.NurseSummary;
import com.hims.app.dto.nurse.NurseUpdateForm;
import com.hims.app.service.NurseService;

@RestController
@RequestMapping("/api/${api.version}/admin/nurses")
public class NurseController {

	private NurseService nurseService;

	public NurseController(NurseService nurseService) {
		super();
		this.nurseService = nurseService;
	}
	
	@GetMapping
	public ResponseEntity<Pager<NurseSummary>> getNurseSummaries(@RequestParam(defaultValue = "0")int pageNumber,
																 @RequestParam(defaultValue = "10")int pageSize) {
		Pager<NurseSummary> pager = nurseService.getNurseList(pageNumber, pageSize);
		return ResponseEntity.ok(pager);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NurseDetail> getNurseDetail(@PathVariable long id) {
		NurseDetail nurseDetail = nurseService.getNurseDetail(id);
		return ResponseEntity.ok(nurseDetail);
	}
	
	@PostMapping
	public ResponseEntity<NurseSummary> registerNurse(@RequestBody NurseRegisterForm form) {
		NurseSummary nurseSummary = nurseService.registerNurse(form);
		return ResponseEntity.ok(nurseSummary);
	}
	
	@PatchMapping
	public ResponseEntity<NurseSummary> updateNurse(@RequestBody NurseUpdateForm form) {
		NurseSummary nurseSummary = nurseService.updateNurse(form);
		return ResponseEntity.ok(nurseSummary);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> removeNurse(@PathVariable long id) {
		nurseService.removeNurse(id);
		return ResponseEntity.ok(Map.of("Status", "Successfully deleted"));
	}
	
}
