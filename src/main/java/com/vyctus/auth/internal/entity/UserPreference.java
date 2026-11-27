/**
 * @Autor: 	 Babür Somer
 * @Date:	 27.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.internal.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_preferences", schema = "auth", uniqueConstraints = {
		@UniqueConstraint(name = "idx_user_preferences", columnNames = { "user_oid", "locale" }) })
public class UserPreference {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "oid", columnDefinition = "UUID", updatable = false, nullable = false)
	private UUID oid;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_oid", nullable = false)
	private User user;

	@NotNull
	@NotBlank
	@Size(max = 20)
	@Column(name = "locale", updatable = false, nullable = false)
	private String localeId;

	@Size(max = 100)
	@Column(name = "timezone")
	private String timezone;

	@NotNull
	@NotBlank
	@Size(max = 20)
	@Column(name = "measurement_system", nullable = false)
	private String measurementSystem;

	@NotNull
	@NotBlank
	@Size(max = 10)
	@Column(name = "energy_unit", nullable = false)
	private String energyUnit;

	/* Audit fields */
	@Version
	private Integer version;

	@CreationTimestamp
	private Timestamp created;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String createdBy;

	@UpdateTimestamp
	private Timestamp updated;

	@Size(max = 50)
	@Column(length = 50)
	private String updatedBy;
	
	@Override
	public UserPreference clone() {
		UserPreference cloned = new UserPreference();
		
		cloned.setOid(this.getOid());
		cloned.setUser(this.getUser());
		cloned.setLocaleId(this.getLocaleId());
		cloned.setTimezone(this.getTimezone());
		cloned.setMeasurementSystem(this.getMeasurementSystem());
		cloned.setEnergyUnit(this.getEnergyUnit());
		
		cloned.setVersion(this.getVersion());
		cloned.setCreated(this.getCreated());
		cloned.setCreatedBy(this.getCreatedBy());
		cloned.setUpdated(this.getUpdated());
		cloned.setUpdatedBy(this.getUpdatedBy());

		return cloned;
	}

}