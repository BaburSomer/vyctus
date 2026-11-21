package com.vyctus.nutrition.internal.mapper;

import org.springframework.stereotype.Component;

import com.vyctus.nutrition.api.model.FoodCreationDTO;
import com.vyctus.nutrition.api.model.FoodDTO;
import com.vyctus.nutrition.internal.entity.Food;
import com.vyctus.nutrition.internal.entity.FoodCategory;

@Component
public class FoodMapper {

	public FoodDTO toDTO(Food food) {
		return new FoodDTO(food.getOid(), food.getCategory().getName(), food.getName(), food.getDescription(), food.getVersion());
	}

	public Food toEntity(FoodCreationDTO dto, FoodCategory foodCategory) {
		Food food = new Food();

		food.setName(dto.getName());
		food.setDescription(dto.getDescription());
		food.setCategory(foodCategory);

		return food;
	}

}