package com.vyctus.diary.internal.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "health_metrics", schema = "diary")

public class HealthMetric {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.CHAR)
	@Column(name = "oid", length = 36, columnDefinition = "UUID", updatable = false, nullable = false)
	private UUID oid;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(name = "user_oid", length = 36, columnDefinition = "UUID", updatable = false, nullable = false)
	private String userOid;

	@NotNull
	@NotBlank
	@Column(name = "measurement_time", nullable = false, unique = true)
	private Timestamp measurementTime;

	@NotNull
	@NotBlank
	@Column(name = "weight", precision = 6, scale = 2, nullable = false)
	private BigDecimal weight;

	@NotNull
	@NotBlank
	@Column(name = "bmi", precision = 6, scale = 2, nullable = false)
	private BigDecimal bmi;

	/* Audit fields */
	@Version
	private Integer version;

	@NotNull
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
}