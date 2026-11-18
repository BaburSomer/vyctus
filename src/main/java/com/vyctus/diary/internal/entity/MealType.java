package com.vyctus.diary.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "meal_types", schema = "diary")

public class MealType {
	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String name;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String description;
}