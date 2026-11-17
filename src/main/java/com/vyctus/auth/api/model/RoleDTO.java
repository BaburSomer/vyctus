package com.vyctus.auth.api.model;

import java.util.UUID;

import lombok.Data;

@Data
public class RoleDTO {

	private UUID   oid;
	private String name;
	private String description;

	/* Audit fields */
	private Integer   version;
}