package com.vyctus.diary.api.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;

@Data
public class HealthMetricDTO {

	private UUID       oid;
	private String     userOid;
	private Timestamp  measurementTime;
	private BigDecimal weight;
	private BigDecimal bmi;

	/* Audit fields */
	private Integer version;
}
