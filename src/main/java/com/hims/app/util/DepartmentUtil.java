package com.hims.app.util;

import com.hims.app.dto.department.DepartmentDetail;
import com.hims.app.dto.department.DepartmentRegisterForm;
import com.hims.app.dto.department.DepartmentSummary;
import com.hims.app.model.Department;

public interface DepartmentUtil {

	Department mapToEntity(DepartmentRegisterForm form);
	
	DepartmentDetail mapToDepartmentDetail(Department department);
	
	DepartmentSummary mapToDepartmentSummary(Department department);
	
}
