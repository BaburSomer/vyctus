ALTER TABLE auth.roles              ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE auth.user_preferences   ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE auth.user_roles         ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE auth.users              ALTER COLUMN version DROP IDENTITY IF EXISTS;

ALTER TABLE diary.health_metrics    ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE diary.meal_items        ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE diary.meals             ALTER COLUMN version DROP IDENTITY IF EXISTS;

ALTER TABLE nutrition.food_nutrition_profiles   ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE nutrition.foods                     ALTER COLUMN version DROP IDENTITY IF EXISTS;

ALTER TABLE recipe.recipe_ingredients   ALTER COLUMN version DROP IDENTITY IF EXISTS;
ALTER TABLE recipe.recipes              ALTER COLUMN version DROP IDENTITY IF EXISTS;