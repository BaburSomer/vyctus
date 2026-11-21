package com.vyctus.nutrition.internal.mapper;

import org.springframework.stereotype.Component;

import com.vyctus.nutrition.api.model.FoodNutritionProfileCreationDTO;
import com.vyctus.nutrition.api.model.FoodNutritionProfileDTO;
import com.vyctus.nutrition.internal.entity.Food;
import com.vyctus.nutrition.internal.entity.FoodNutritionProfile;
import com.vyctus.nutrition.internal.entity.FoodNutritionProfileId;
import com.vyctus.nutrition.internal.entity.PreparationState;
import com.vyctus.nutrition.internal.entity.Unit;

@Component
public class FoodNutritionProfileMapper {

    public FoodNutritionProfile toEntity(
            FoodNutritionProfileCreationDTO dto,
            Food food,
            PreparationState preparationState,
            Unit referenceUnit) {

        FoodNutritionProfile profile = new FoodNutritionProfile();

        profile.setId(new FoodNutritionProfileId(
            food.getOid(),
            preparationState.getName()
        ));

        profile.setFood(food);
        profile.setPreparationState(preparationState);
        profile.setRefAmount(dto.getRefAmount());
        profile.setRefUnit(referenceUnit);
        profile.setCalories(dto.getCalories());
        profile.setProteins(dto.getProteins());
        profile.setCarbs(dto.getCarbs());
        profile.setFat(dto.getFat());
        profile.setFiber(dto.getFiber());
        profile.setDataSource(dto.getDataSource());

        return profile;
    }

    public FoodNutritionProfileDTO toDTO(FoodNutritionProfile profile) {

        return new FoodNutritionProfileDTO(
            profile.getFood().getOid(),
            profile.getPreparationState().getName(),
            profile.getRefAmount(),
            profile.getRefUnit().getName(),
            profile.getCalories(),
            profile.getProteins(),
            profile.getCarbs(),
            profile.getFat(),
            profile.getFiber(),
            profile.getDataSource(),
            profile.getVersion()
        );
    }
}