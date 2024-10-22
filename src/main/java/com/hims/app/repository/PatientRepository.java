package com.hims.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.hims.app.model.Patient;
import com.hims.app.dto.patient.InfoCheckDTO;
import com.hims.app.dto.patient.PatientDetail;
import com.hims.app.dto.patient.PatientSummary;

@Service
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("SELECT new com.hims.app.dto.patient.PatientSummary(p.id, p.firstName, p.lastName, p.gender, p.email,"
			+ " p.phone) FROM Patient p")
	Page<PatientSummary> getPatientSummaries(Pageable pageable);
	
	@Query("SELECT new com.hims.app.dto.patient.PatientDetail(p.id, p.firstName, p.lastName, p.dateOfBirth, p.email,"
			+ "p.phone, p.address, p.gender) FROM Patient p WHERE p.id = :id")
	PatientDetail getPatientDetail(@Param("id") long id);
	
	@Query("SELECT new com.hims.app.dto.patient.InfoCheckDTO(p.email, p.phone) FROM Patient p WHERE p.email = :email OR"
			+ " p.phone = :phone")
	InfoCheckDTO checkInfoExists(@Param("email") String email, @Param("phone") String phone);
	
	@Query("SELECT new com.hims.app.dto.patient.InfoCheckDTO(p.email, p.phone) FROM Patient p WHERE (p.email = :email OR"
			+ " p.phone = :phone) AND p.id <> :id")
	InfoCheckDTO checkInfoExists(@Param("id") long id, @Param("email") String email, @Param("phone") String phone);

	Optional<Patient> findByEmail(String email);
	
}
