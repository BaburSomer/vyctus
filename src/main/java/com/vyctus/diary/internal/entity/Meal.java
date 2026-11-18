package com.vyctus.diary.internal.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "meals", schema = "diary")

public class Meal {

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
	@Size(max = 100)
	@Column(name = "meal_type", updatable = false, nullable = false)
	private String mealType;

	@NotNull
	@NotBlank
	@Column(name = "meal_date", nullable = false)
	private Date mealDate;

	@NotNull
	@NotBlank
	@Column(name = "meal_time", nullable = false)
	private Time mealTime;

	@Column(name = "notes")
	private String notes;

	@OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
	private final Set<MealItem> mealItems = new HashSet<>();

	public void addMealItem(MealItem mealItem) {
		mealItems.add(mealItem);
		mealItem.setMeal(this);
	}

	public void removeMealItem(MealItem mealItem) {
		mealItems.remove(mealItem);
		mealItem.setMeal(null);
	}

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