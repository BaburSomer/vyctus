package com.vyctus.nutrition.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class FoodNutritionProfileCreationDTO {

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
}