package com.hims.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hims.app.dto.Pager;
import com.hims.app.dto.department.DepartmentDetail;
import com.hims.app.dto.department.DepartmentOption;
import com.hims.app.dto.department.DepartmentRegisterForm;
import com.hims.app.dto.department.DepartmentSummary;
import com.hims.app.dto.department.DepartmentUpdateForm;
import com.hims.app.exception.DepartmentExistsException;
import com.hims.app.model.Department;
import com.hims.app.repository.DepartmentRepository;
import com.hims.app.service.DepartmentService;
import com.hims.app.util.DepartmentUtil;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentUtil departmentUtil;
	private DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentUtil departmentUtil, DepartmentRepository departmentRepository) {
		this.departmentUtil = departmentUtil;
		this.departmentRepository = departmentRepository;
	}
	
	@Override
	public DepartmentSummary registerDepartment(DepartmentRegisterForm form) throws DepartmentExistsException {
		Department department = departmentUtil.mapToEntity(form);
		department = departmentRepository.save(department);
		return departmentUtil.mapToDepartmentSummary(department);
	}
	
	@Transactional
	@Override
	public DepartmentSummary updateDepartment(DepartmentUpdateForm form) throws DepartmentExistsException {
		Optional<Department> result = departmentRepository.findById(form.id());
		if(result.isPresent()) {
			Department department = result.get();
			department.setName(form.name());
			department.setDescription(form.description());
			return departmentUtil.mapToDepartmentSummary(department);
		}
		return null;
	}
	
	@Transactional
	@Override
	public void removeDepartment(long id) {
		Optional<Department> result = departmentRepository.findById(id);
		if(result.isPresent()) {
			Department department = result.get();
			department.setActive(false);
		}
	}
	
	
	@Override
	public Pager<DepartmentSummary> getDepartmentList(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<DepartmentSummary> page = departmentRepository.getDepartmentSummaries(pageable);
		Pager<DepartmentSummary> pager = new Pager<DepartmentSummary>(page.getContent(), page.getTotalElements(), page.getTotalPages());
		return pager;
	}
	
	@Override
	public List<DepartmentOption> getDepartmentOptions() {
		return departmentRepository.getDepartmentOptions();
	}
	
	@Transactional
	@Override
	public DepartmentDetail getDepartmentDetail(long id) {
		Optional<Department> result = departmentRepository.findById(id);
		if(result.isPresent()) {
			Department department = result.get();
			return departmentUtil.mapToDepartmentDetail(department);
		}
		return null;
	}
	
	@Override
	public List<DepartmentSummary> getDepartmentsForPatient() {
		return departmentRepository.getDepartmentsForPatient();
	}
	
	
}
