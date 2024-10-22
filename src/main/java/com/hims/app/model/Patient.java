package com.hims.app.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.hims.app.model.Appointment;
import com.hims.app.model.field.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable {

	@Serial
	private static final long serialVersionUID = -8877313766533641004L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String email;
	private String password;
	private String phone;
	private String address;
	private boolean active;
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;
	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
	@PrePersist
	protected void onCreate() {
		this.active = true;
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now(); 
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = LocalDateTime.now();
	}

	
}
