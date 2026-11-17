package com.vyctus.auth.api.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {

	private UUID         oid;
	private String       firstName;
	private String       lastName;
	private Date         birthDate;
	private BigDecimal   height;
	private Set<RoleDTO> roles;
	
	/* Audit fields */
	private Integer   version;
}