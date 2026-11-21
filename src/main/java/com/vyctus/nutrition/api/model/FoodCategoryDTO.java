package com.vyctus.nutrition.api.model;

import lombok.Data;

@Data
public class FoodCategoryDTO {

	private String  name;
	private String  description;
	private boolean enabled;
}
