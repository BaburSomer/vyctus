package com.vyctus.diary.api.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.vyctus.diary.internal.entity.MealItem;

import lombok.Data;

@Data
public class MealDTO {

	private UUID                oid;
	private String              userOid;
	private String              mealType;
	private Date                mealDate;
	private Time                mealTime;
	private BigDecimal          totalCalories;
	private String              notes;
	private final Set<MealItem> mealItems = new HashSet<>();

	/* Audit fields */
	private Integer version;
}
