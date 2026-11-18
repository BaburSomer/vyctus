package com.vyctus.diary.internal.mapper;

import org.mapstruct.Mapper;

import com.vyctus.diary.api.model.MealTypeDTO;
import com.vyctus.diary.internal.entity.MealType;

@Mapper(componentModel = "spring")
public interface MealTypeMapper {

	MealType toEntity(MealTypeDTO dto);

	MealTypeDTO toDto(MealType entity);
}