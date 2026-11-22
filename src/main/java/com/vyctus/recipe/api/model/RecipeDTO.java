/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.recipe.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeDTO {

	private UUID       oid;
	private String     name;
	private String     description;
	private String     instructions;
	private Integer    servings;
	private BigDecimal yieldAmount;
	private UUID       yieldUnitOid;
	private String     recipeSource;

	/* Audit fields */
	private Integer version;
}