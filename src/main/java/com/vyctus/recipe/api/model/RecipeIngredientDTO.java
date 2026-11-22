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
public class RecipeIngredientDTO {

	private UUID       oid;
	private UUID       recipeOid;
	private UUID       foodOid;
	private UUID       unitOid;
	private BigDecimal amount;

	/* Audit fields */
	private Integer version;
}