ALTER TABLE recipe.recipes DROP COLUMN yield_unit_oid;
ALTER TABLE recipe.recipes ADD  COLUMN yield_unit_oid UUID NOT NULL;
    
ALTER TABLE recipe.recipes ALTER COLUMN yield_amount TYPE numeric(7,2);
ALTER TABLE recipe.recipes ALTER COLUMN yield_amount SET NOT NULL;

ALTER TABLE recipe.recipes ADD CONSTRAINT chk_recipe_yield_unit CHECK (yield_amount IS NULL OR yield_unit_oid IS NOT NULL);

ALTER TABLE recipe.recipe_ingredients ALTER COLUMN amount TYPE numeric(7,2);
ALTER TABLE recipe.recipe_ingredients DROP COLUMN amount_source;
