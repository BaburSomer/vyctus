package com.vyctus.nutrition.internal.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FoodNutritionProfileId implements Serializable {

	private static final long serialVersionUID = 2362716882314614072L;

	@Column(name = "food_oid", nullable = false)
	private UUID foodOid;

	@Column(name = "preparation_state", length = 100, nullable = false)
	private String prepState;

}