CREATE TABLE IF NOT EXISTS nutrition.units (
    name varchar(100) NOT NULL UNIQUE,
    description varchar(100),
    PRIMARY KEY (name)
);
CREATE UNIQUE INDEX idx_units_1 ON nutrition.units (name);

-- 'Enumaration: Meat, Vegetables, Fruits, Dairy, Grains, Beverages, Oils.'
CREATE TABLE IF NOT EXISTS nutrition.food_categories (
    name varchar(100) NOT NULL UNIQUE,
    description varchar(100),
    PRIMARY KEY (name)
);
CREATE UNIQUE INDEX idx_food_categories_1 ON nutrition.food_categories (name);

-- Enumaration: RAW, COOKED, DRIED, READY_TO_EAT
CREATE TABLE IF NOT EXISTS nutrition.preparation_states (
    name varchar(100) NOT NULL UNIQUE,
    description varchar(100),
    PRIMARY KEY (name)
) ;
CREATE UNIQUE INDEX idx_preparation_states_1 ON nutrition.preparation_states (name);

-- A food represents an individual food item or food product with nutritional information.
CREATE TABLE IF NOT EXISTS nutrition.foods (
    oid uuid NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    food_category varchar(100) NOT NULL,
    name varchar(100) NOT NULL UNIQUE,
    description text,
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_foods_1 ON nutrition.foods (oid);
ALTER TABLE nutrition.foods ADD CONSTRAINT foods_fk1 FOREIGN KEY (food_category) REFERENCES nutrition.food_categories(name);

CREATE TABLE IF NOT EXISTS nutrition.food_nutrition_profiles (
    food_oid uuid NOT NULL DEFAULT gen_random_uuid(),
    preparation_state varchar(100) NOT NULL,
    reference_amount decimal(6,2) NOT NULL,
    reference_unit varchar(100) NOT NULL,
    calories decimal(6,2) NOT NULL,
    proteins decimal(6,2),
    carbs decimal(6,2),
    fat decimal(6,2),
    fiber decimal(6,2),
    data_source varchar(1000),
    created timestamp NOT NULL,
    created_by varchar(50) NOT NULL,
    updated timestamp,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (food_oid, preparation_state)
);
CREATE UNIQUE INDEX idx_food_nutrition_profiles_1 ON nutrition.food_nutrition_profiles (food_oid, preparation_state);
ALTER TABLE nutrition.food_nutrition_profiles ADD CONSTRAINT food_nutrition_profiles_fk1 FOREIGN KEY (reference_unit)    REFERENCES nutrition.units(name);
ALTER TABLE nutrition.food_nutrition_profiles ADD CONSTRAINT food_nutrition_profiles_fk2 FOREIGN KEY (food_oid)          REFERENCES nutrition.foods (oid);
ALTER TABLE nutrition.food_nutrition_profiles ADD CONSTRAINT food_nutrition_profiles_fk3 FOREIGN KEY (preparation_state) REFERENCES nutrition.preparation_states (name);
