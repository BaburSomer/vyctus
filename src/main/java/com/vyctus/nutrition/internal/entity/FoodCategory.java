package com.vyctus.nutrition.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "food_categories", schema = "nutrition")
public class FoodCategory {

	@Id
	@Column(name = "name", length = 100, nullable = false, unique = true, updatable = false)
	private String name;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

}