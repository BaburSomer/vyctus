package com.vyctus.nutrition.internal.mapper;

import org.mapstruct.Mapper;

import com.vyctus.nutrition.api.model.PreparationStateDTO;
import com.vyctus.nutrition.internal.entity.PreparationState;

@Mapper(componentModel = "spring")
public interface PreparationStateMapper {

	PreparationState toEntity(PreparationStateDTO dto);

	PreparationStateDTO toDto(PreparationState entity);

}