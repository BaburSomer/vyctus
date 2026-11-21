package com.vyctus.nutrition.internal.mapper;

import org.mapstruct.Mapper;

import com.vyctus.nutrition.api.model.FoodCategoryDTO;
import com.vyctus.nutrition.internal.entity.FoodCategory;

@Mapper(componentModel = "spring")
public interface FoodCategoryMapper {

	FoodCategory toEntity(FoodCategoryDTO dto);

	FoodCategoryDTO toDto(FoodCategory entity);

}
