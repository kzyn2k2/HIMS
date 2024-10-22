package com.hims.app.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

import com.hims.app.model.field.DoctorRank;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Employee implements Serializable {

	@Serial
	private static final long serialVersionUID = -345355243669004555L;
	private String licenseNumber;
	@Enumerated(EnumType.ORDINAL)
	private DoctorRank doctorRank;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = LocalDateTime.now();
		this.updatedOn = LocalDateTime.now(); 
		this.active = true;
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = LocalDateTime.now();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(licenseNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(licenseNumber, other.licenseNumber);
	}

	@Override
	public Collection<? extends GrantedAuthority> provideRoles() {
		return List.of(this.doctorRank);
	}
	
}
