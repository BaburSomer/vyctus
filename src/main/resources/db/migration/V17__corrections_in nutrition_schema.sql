ALTER TABLE nutrition.food_nutrition_profiles
    ALTER COLUMN food_oid DROP DEFAULT;

ALTER TABLE nutrition.food_categories    
    ADD COLUMN enabled boolean NOT NULL DEFAULT true;

ALTER TABLE nutrition.preparation_states    
    ADD COLUMN enabled boolean NOT NULL DEFAULT true;

ALTER TABLE nutrition.units    
    ADD COLUMN enabled boolean NOT NULL DEFAULT true;