package com.vyctus.diary.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vyctus.diary.api.model.MealItemDTO;
import com.vyctus.diary.internal.entity.MealItem;

@Mapper(componentModel = "spring")
public interface MealItemMapper {

	@Mapping(target = "created", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	MealItem toEntity(MealItemDTO dto);

	MealItemDTO toDto(MealItem entity);
}