package com.vyctus.diary.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.vyctus.diary.internal.entity.Meal;

import lombok.Data;

@Data
public class MealItemDTO {

	private UUID       oid;
	private String     mealOid;
	private String     recipeOid;
	private String     foodOid;
	private String     unit;
	private BigDecimal amount;
	private String     amountSource;
	private Meal       meal;

	/* Audit fields */
	private Integer version;
}
