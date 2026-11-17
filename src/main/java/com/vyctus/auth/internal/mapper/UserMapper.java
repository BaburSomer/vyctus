package com.vyctus.auth.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vyctus.auth.api.model.RoleDTO;
import com.vyctus.auth.api.model.UserDTO;
import com.vyctus.auth.internal.entity.User;
import com.vyctus.auth.internal.entity.UserRole;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
 
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	User toEntity(UserDTO dto);

   @Mapping(target = "roles", source = "userRoles")
   UserDTO toDto(User entity);

   @Mapping(target = "oid", source = "role.oid")
   @Mapping(target = "name", source = "role.name")
   @Mapping(target = "description", source = "role.description")
   RoleDTO userRoleToRoleDto(UserRole userRole);
}
