package com.vyctus.nutrition.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodNutritionProfileDTO {

	private UUID       foodOid;
	private String     prepState;
	private BigDecimal refAmount;
	private String     refUnit;
	private BigDecimal calories;
	private BigDecimal proteins;
	private BigDecimal carbs;
	private BigDecimal fat;
	private BigDecimal fiber;
	private String     dataSource;

	/* Audit fields */
	private Integer version;
}