/**
 * @Autor: 	 Babür Somer
 * @Date:	 27.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.internal.mapper;

import org.mapstruct.Mapping;

import com.vyctus.auth.api.model.UserPreferenceDTO;
import com.vyctus.auth.internal.entity.UserPreference;

public interface UserPreferenceMapper {

	@Mapping(target = "created", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	UserPreference toEntity(UserPreferenceDTO dto);

	UserPreferenceDTO toDto(UserPreference entity);

}
