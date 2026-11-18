package com.vyctus.diary.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vyctus.diary.api.model.MealDTO;
import com.vyctus.diary.internal.entity.Meal;

@Mapper(componentModel = "spring")
public interface MealMapper {

	@Mapping(target = "created", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	Meal toEntity(MealDTO dto);

	MealDTO toDto(Meal entity);
}