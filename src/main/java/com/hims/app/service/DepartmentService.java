package com.hims.app.service;

import java.util.List;

import com.hims.app.dto.Pager;
import com.hims.app.dto.department.DepartmentRegisterForm;
import com.hims.app.dto.department.DepartmentDetail;
import com.hims.app.dto.department.DepartmentOption;
import com.hims.app.dto.department.DepartmentSummary;
import com.hims.app.dto.department.DepartmentUpdateForm;
import com.hims.app.exception.DepartmentExistsException;
import com.hims.app.model.Department;

public interface DepartmentService {

	DepartmentSummary registerDepartment(DepartmentRegisterForm form) throws DepartmentExistsException;
	
	DepartmentSummary updateDepartment(DepartmentUpdateForm form) throws DepartmentExistsException;
	
	void removeDepartment(long id);
	
	Pager<DepartmentSummary> getDepartmentList(int pageNumber, int pageSize);
	
	List<DepartmentOption> getDepartmentOptions();
	
	DepartmentDetail getDepartmentDetail(long id);
	
	List<DepartmentSummary> getDepartmentsForPatient();
}
