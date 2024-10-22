package com.hims.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hims.app.model.Department;
import com.hims.app.dto.department.DepartmentDetail;
import com.hims.app.dto.department.DepartmentOption;
import com.hims.app.dto.department.DepartmentSummary;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("SELECT new com.hims.app.dto.department.DepartmentSummary(d.id, d.name, d.description) FROM Department d WHERE d.active = true")
	Page<DepartmentSummary> getDepartmentSummaries(Pageable pageable);
	
	@Query("SELECT new com.hims.app.dto.department.DepartmentOption(d.id, d.name) FROM Department d WHERE d.active = true")
	List<DepartmentOption> getDepartmentOptions();
	
	@Query("SELECT new com.hims.app.dto.department.DepartmentSummary(d.id, d.name, d.description) FROM Department d WHERE d.access = true AND d.active = true")
	List<DepartmentSummary> getDepartmentsForPatient();
	
	Optional<Department> findByName(String name);
	
}
