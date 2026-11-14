CREATE TABLE IF NOT EXISTS recipe.recipes (
    oid uuid NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    name varchar(100) NOT NULL UNIQUE,
    description text,
    instructions text,
    servings int NOT NULL,
    yield_amount int,
    yield_unit_oid int,
    recipe_source varchar(255),
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_recipe_1 ON recipe.recipes (oid);


CREATE TABLE IF NOT EXISTS recipe.recipe_ingredients (
    oid uuid NOT NULL DEFAULT gen_random_uuid(),
    recipe_oid uuid NOT NULL,
    food_oid uuid NOT NULL,
    unit_oid uuid NOT NULL,
    amount decimal(6,2) NOT NULL,
    amount_source varchar(255),
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_recipe_ingredients_1 ON recipe.recipe_ingredients (oid);
CREATE UNIQUE INDEX idx_recipe_ingredients_2 ON recipe.recipe_ingredients (recipe_oid, food_oid);

ALTER TABLE recipe.recipe_ingredients   ADD CONSTRAINT recipe_ingredients_fk1   FOREIGN KEY (recipe_oid)    REFERENCES recipe.recipes(oid);

