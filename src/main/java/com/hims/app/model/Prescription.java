package com.hims.app.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.hims.app.model.MedicalRecord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription implements Serializable {

	@Serial
	private static final long serialVersionUID = -872011745520614331L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@ManyToOne
	@JoinColumn(name = "medical_record_id")
	private MedicalRecord medicalRecord;
	private String medicine;
	private String dosage;
	private String instruction;
	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;

	@PrePersist
	protected void onCreate() {
		this.createdOn = LocalDateTime.now();
	}
	
}
