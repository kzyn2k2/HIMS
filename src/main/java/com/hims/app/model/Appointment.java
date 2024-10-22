package com.hims.app.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hims.app.model.field.AppointmentStatus;
import com.hims.app.model.Doctor;
import com.hims.app.model.MedicalRecord;
import com.hims.app.model.Patient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {

	@Serial
	private static final long serialVersionUID = 1476956462619828317L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	@OneToOne(mappedBy = "appointment")
	private MedicalRecord medicalRecord;
	@Enumerated(EnumType.ORDINAL)
	private AppointmentStatus status;
	private LocalDateTime appointmentDate;
	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now(); 
		this.status = AppointmentStatus.WAITING;
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = LocalDateTime.now();
	}
	
}
