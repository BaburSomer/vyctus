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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "meal_items", schema = "diary")

public class MealItem {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.CHAR)
	@Column(name = "oid", length = 36, columnDefinition = "UUID", updatable = false, nullable = false)
	private UUID oid;

	@NotNull
	@NotBlank
	@Column(name = "meal_oid", length = 36, columnDefinition = "UUID", nullable = false)
	private String mealOid;

	@Column(name = "recipe_oid", length = 36, columnDefinition = "UUID")
	private String recipeOid;

	@Column(name = "food_oid", length = 36, columnDefinition = "UUID")
	private String foodOid;

	@NotNull
	@NotBlank
	@Column(name = "unit", length = 100)
	private String unit;

	@NotNull
	@NotBlank
	@Column(name = "amount", precision = 6, scale = 2, nullable = false)
	private BigDecimal amount;

	@Column(name = "amount_source", length = 255)
	private String amountSource;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "meal_oid", nullable = false)
	private Meal meal;

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