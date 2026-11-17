package com.vyctus.auth.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vyctus.auth.api.model.RoleDTO;
import com.vyctus.auth.internal.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
 
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	Role toEntity(RoleDTO dto);

	RoleDTO toDto(Role entity);
}
