package com.vyctus.nutrition.internal.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "food_nutrition_profiles", schema = "nutrition")
public class FoodNutritionProfile {

	@EmbeddedId
	private FoodNutritionProfileId id;

	@NotNull
	@NotBlank
	@Column(name = "reference_amount", nullable = false, precision = 6, scale = 2)
	private BigDecimal refAmount;

	@NotNull
   @DecimalMin("0.0")
   @Column(name = "calories", nullable = false, precision = 6, scale = 2)
	private BigDecimal calories;

	@NotNull
	@Column(name = "proteins", precision = 6, scale = 2)
	private BigDecimal proteins;

	@NotNull
	@Column(name = "carbs", precision = 6, scale = 2)
	private BigDecimal carbs;

	@NotNull
	@Column(name = "fat", precision = 6, scale = 2)
	private BigDecimal fat;

	@NotNull
	@Column(name = "fiber", precision = 6, scale = 2)
	private BigDecimal fiber;

	@Size(max = 1000)
	@Column(name = "data_source")
	private String dataSource;

	@MapsId("foodOid")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "food_oid", nullable = false)
	private Food food;

	@MapsId("prepState")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "preparation_state", referencedColumnName = "name", nullable = false)
	private PreparationState preparationState;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "reference_unit", nullable = false)
	private Unit refUnit;

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