package com.hims.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hims.app.model.Doctor;
import com.hims.app.dto.doctor.DoctorDepartmentInfo;
import com.hims.app.dto.doctor.DoctorDetail;
import com.hims.app.dto.doctor.DoctorSummary;
import com.hims.app.dto.employee.RegisterInfoCheckDTO;
import com.hims.app.dto.employee.UpdateInfoCheckDTO;
import com.hims.app.model.field.DoctorRank;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("select new com.hims.app.dto.doctor.DoctorSummary(d.id, d.firstName, d.lastName, d.licenseNumber,d.department.name,d.doctorRank)"
			+ " from Doctor d WHERE d.active = true")
	Page<DoctorSummary> getDoctorSummaries(Pageable pageable);
	
	
	@Query("SELECT new com.hims.app.dto.doctor.DoctorDepartmentInfo(d.id, d.firstName, d.lastName, d.doctorRank) FROM "
			+ "Doctor d WHERE d.department.id = :departmentId")
	Page<DoctorDepartmentInfo> getDoctorsInDepartment(@Param("departmentId") long departmentId, Pageable pageable);
	
	@Query("SELECT new com.hims.app.dto.doctor.DoctorDetail(d.id, d.firstName, d.lastName, d.email,d.phone,"
			+ "d.doctorRank, d.licenseNumber, d.department.name, d.employedDate, d.active, d.gender) FROM "
			+ "Doctor d WHERE d.id = :id")
	DoctorDetail getDoctorDetail(@Param("id") long id);
	
	@Query("SELECT new com.hims.app.dto.employee.RegisterInfoCheckDTO(d.email, d.phone, d.licenseNumber) FROM Doctor d where d.email = :email OR "
			+ "d.phone = :phone OR d.licenseNumber = :license")
	RegisterInfoCheckDTO checkRegisterInfoExists(@Param("email") String email, @Param("phone") String phone, @Param("license") String licenseNumber);
	
	@Query("SELECT new com.hims.app.dto.employee.UpdateInfoCheckDTO(d.email, d.phone) FROM Doctor d where (d.email = :email OR d.phone = :phone) AND d.id <> :id")
	UpdateInfoCheckDTO checkUpdateInfoExists(@Param("id") long id, @Param("email") String email, @Param("phone") String phone);
	
	@Query("SELECT new com.hims.app.dto.doctor.DoctorDepartmentInfo(d.id, d.firstName, d.lastName, d.doctorRank) FROM "
			+ "Doctor d WHERE d.department.id = :departmentId AND d.doctorRank IN :ranks AND d.active = true")
	List<DoctorDepartmentInfo> getDoctorsForBooking(@Param("departmentId") long departmentId, @Param("ranks") List<DoctorRank> rank);
	
	@Modifying
	@Query("UPDATE Doctor d SET d.active = false WHERE d.id = :id")
	void removeDoctor(@Param("id") long id);
	
	
}
