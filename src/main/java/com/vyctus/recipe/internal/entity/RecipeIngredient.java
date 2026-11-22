/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.recipe.internal.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "recipe_ingredients", schema = "recipe")
public class RecipeIngredient {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.CHAR)
	@Column(name = "oid", length = 36, columnDefinition = "UUID", updatable = false, nullable = false)
	private UUID oid;

//	private UUID    recipeOid;
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "recipe_oid", nullable = false)
	private Recipe recipe;

   // Cross-schema references: UUID only
	@Column(name = "food_oid", nullable = false)
	private UUID foodOid;

	// Cross-schema references: UUID only
	@Column(name = "unit_oid", nullable = false)
	private UUID unitOid;

   @NotNull
   @DecimalMin("0.0")
	@Column(name = "amount", nullable = false, precision = 7, scale = 3)
	private BigDecimal amount;

	/* Audit fields */
	@Version
	private Integer version;

	@NotNull
	@CreationTimestamp
	private Timestamp created;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String createdBy;

	@UpdateTimestamp
	private Timestamp updated;

	@Size(max = 50)
	@Column(length = 50)
	private String updatedBy;

}