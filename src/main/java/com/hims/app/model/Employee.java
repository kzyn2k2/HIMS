package com.hims.app.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.hims.app.model.field.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Employee implements Serializable{

	@Serial
	private static final long serialVersionUID = 1263515820101754224L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected long id;
	protected String firstName;
	protected String lastName;
	protected LocalDate employedDate;
	protected String email;
	protected String phone;
	@Enumerated(EnumType.ORDINAL)
	protected Gender gender;
	protected boolean active;
	@Column(name = "created_on", updatable = false)
	protected LocalDateTime createdOn;
	@Column(name = "updated_on")
	protected LocalDateTime updatedOn;
	
	@PrePersist
	public void prePersist() {
		this.active = true;
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedOn = LocalDateTime.now();
	}

	public abstract Collection<? extends GrantedAuthority> provideRoles();
	
}
