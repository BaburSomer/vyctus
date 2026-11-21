package com.vyctus.nutrition.api.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodDTO {

	private UUID   oid;
	private String foodCategory;
	private String name;
	private String description;

	/* Audit fields */
	private Integer version;

}