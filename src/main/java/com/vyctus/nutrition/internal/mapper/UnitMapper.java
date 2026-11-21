package com.vyctus.nutrition.internal.mapper;

import org.mapstruct.Mapper;

import com.vyctus.nutrition.api.model.UnitDTO;
import com.vyctus.nutrition.internal.entity.Unit;

@Mapper(componentModel = "spring")
public interface UnitMapper {

	Unit toEntity(UnitDTO dto);

	UnitDTO toDto(Unit entity);

}
