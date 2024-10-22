package com.hims.app.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.hims.app.model.Appointment;
import com.hims.app.model.Prescription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord implements Serializable{

	@Serial
	private static final long serialVersionUID = -3571908794151705862L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@OneToOne()
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;
	private String diagnosis;
	private String treatment;
	private String note;
	@OneToMany(mappedBy = "medicalRecord")
	private List<Prescription> prescriptions;
	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = LocalDateTime.now();
	}
	
}
