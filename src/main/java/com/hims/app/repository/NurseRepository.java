package com.hims.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hims.app.model.Nurse;
import com.hims.app.dto.employee.RegisterInfoCheckDTO;
import com.hims.app.dto.employee.UpdateInfoCheckDTO;
import com.hims.app.dto.nurse.NurseDepartmentInfo;
import com.hims.app.dto.nurse.NurseDetail;
import com.hims.app.dto.nurse.NurseSummary;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

	@Query("SELECT new com.hims.app.dto.nurse.NurseSummary(n.id, n.firstName, n.lastName, n.licenseNumber,"
			+ "n.department.name, n.nurseRank) FROM Nurse n")
	Page<NurseSummary> getNurseSummaries(Pageable pageable);
	
	@Query("SELECT new com.hims.app.dto.nurse.NurseDepartmentInfo(n.id, n.firstName, n.lastName, n.nurseRank) FROM Nurse n"
			+ " WHERE n.department.id = :departmentId")
	Page<NurseDepartmentInfo> getNursesInDepartment(@Param("departmentId") long departmentId, Pageable pageable);
	
	@Query("SELECT new com.hims.app.dto.nurse.NurseDetail(n.id, n.firstName, n.lastName, n.employedDate, n.phone,"
			+ "n.email, n.department.name, n.gender, n.licenseNumber, n.active, n.nurseRank) from Nurse n WHERE n.id = :id")
	NurseDetail getNurseDetail(@Param("id") long id);
	
	@Query("SELECT new com.hims.app.dto.employee.RegisterInfoCheckDTO(n.email, n.phone, n.licenseNumber) from Nurse n "
			+ "WHERE n.email = :email OR n.phone = :phone OR n.licenseNumber = :license")
	RegisterInfoCheckDTO checkRegisterInfoExists(@Param("email") String email, @Param("phone") String phone, @Param("license") String license);
	
	@Query("SELECT new com.hims.app.dto.employee.UpdateInfoCheckDTO(n.email, n.phone) FROM Nurse n WHERE (n.email = :email OR n.phone = :phone) AND n.id <> :id")
	UpdateInfoCheckDTO checkUpdateInfoExists(@Param("id") long id, @Param("email") String email, @Param("phone") String phone);
	
	@Modifying
	@Query("UPDATE Nurse n SET n.active = false WHERE n.id = :id")
	void removeNures(@Param("id") long id);
}
