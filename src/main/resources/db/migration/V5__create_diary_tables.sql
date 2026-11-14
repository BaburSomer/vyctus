CREATE TABLE IF NOT EXISTS diary.meal_types (
    name varchar(100) NOT NULL UNIQUE,
    description text,
    PRIMARY KEY (name)
);
CREATE UNIQUE INDEX idx_meal_types_1 ON diary.meal_types (name);

CREATE TABLE IF NOT EXISTS diary.meals (
    oid uuid NOT NULL DEFAULT gen_random_uuid(),
    user_oid uuid NOT NULL,
    meal_type varchar(100) NOT NULL,
    meal_date date NOT NULL,
    meal_time time NOT NULL,
    notes text,
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_meals_1 ON diary.meals (oid);
CREATE UNIQUE INDEX idx_meals_2 ON diary.meals (user_oid, meal_type, meal_date, meal_time);
ALTER TABLE diary.meals ADD CONSTRAINT meals_fk1 FOREIGN KEY (user_oid)  REFERENCES auth.users(oid);
ALTER TABLE diary.meals ADD CONSTRAINT meals_fk2 FOREIGN KEY (meal_type) REFERENCES diary.meal_types(name);

CREATE TABLE IF NOT EXISTS diary.meal_items (
    oid uuid NOT NULL DEFAULT gen_random_uuid(),
    meal_oid uuid NOT NULL,
    recipe_oid uuid,
    food_oid uuid,
    unit varchar(100) NOT NULL,
    amount decimal(6,2) NOT NULL,
    amount_source varchar(255),
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_meal_items_1 ON diary.meal_items (oid);
ALTER TABLE diary.meal_items ADD CONSTRAINT meal_items_fk1 FOREIGN KEY (meal_oid)   REFERENCES diary.meals(oid);
ALTER TABLE diary.meal_items ADD CONSTRAINT meal_items_fk2 FOREIGN KEY (recipe_oid) REFERENCES recipe.recipes(oid);
ALTER TABLE diary.meal_items ADD CONSTRAINT meal_items_fk3 FOREIGN KEY (food_oid)   REFERENCES nutrition.foods(oid);
ALTER TABLE diary.meal_items ADD CONSTRAINT meal_items_fk4 FOREIGN KEY (unit)   REFERENCES nutrition.units(name);
ALTER TABLE diary.meal_items ADD CONSTRAINT chk_meal_item_target
CHECK (
    (food_oid IS NOT NULL AND recipe_oid IS NULL)
    OR
    (food_oid IS NULL AND recipe_oid IS NOT NULL)
 );

CREATE TABLE IF NOT EXISTS diary.health_metrics (
    oid uuid NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    user_oid uuid NOT NULL,
    measurement_time timestamp NOT NULL UNIQUE,
    weight decimal(6,2) NOT NULL,
    bmi decimal(6,2) NOT NULL,
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_health_metrics_1 ON diary.health_metrics (oid);
CREATE UNIQUE INDEX idx_health_metrics_2 ON diary.health_metrics (user_oid, measurement_time);
ALTER TABLE diary.health_metrics ADD CONSTRAINT meals_fk1 FOREIGN KEY (user_oid)  REFERENCES auth.users(oid);
