/**
 * @Autor: 	 Babür Somer
 * @Date:	 27.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.api.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferenceDTO {

	private UUID   oid;
	private UUID   userOid;
	private String localeId;
	private String timezone;
	private String measurementSystem;
	private String energyUnit;

	/* Audit fields */
	private Integer version;
}