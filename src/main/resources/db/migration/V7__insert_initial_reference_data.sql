INSERT INTO auth.roles(name, description, created_by) VALUES ('ADMIN', 'Administrator of the application', 'ADMIN');
INSERT INTO auth.roles(name, description, created_by) VALUES ('USER', 'An ordinary user of the application', 'ADMIN');

INSERT INTO diary.meal_types(name, description) VALUES ('BREAKFAST', 'DESCR_BREAKFAST');
INSERT INTO diary.meal_types(name, description) VALUES ('LUNCH',     'DESCR_LUNCH');
INSERT INTO diary.meal_types(name, description) VALUES ('DINNER',    'DESCR_DINNER');
INSERT INTO diary.meal_types(name, description) VALUES ('SNACK',     'DESCR_SNACK');

INSERT INTO nutrition.units(name, description) VALUES ('MASS',      'DESCR_MASS');
INSERT INTO nutrition.units(name, description) VALUES ('VOLUME',    'DESCR_VOLUME');
INSERT INTO nutrition.units(name, description) VALUES ('COUNT',     'DESCR_COUNT');
INSERT INTO nutrition.units(name, description) VALUES ('SERVING',   'DESCR_SERVING');

INSERT INTO nutrition.preparation_states(name, description) VALUES ('RAW',      'DESCR_RAW');
INSERT INTO nutrition.preparation_states(name, description) VALUES ('BOILED',   'DESCR_BOILED');
INSERT INTO nutrition.preparation_states(name, description) VALUES ('GRILLED',  'DESCR_GRILLED');
INSERT INTO nutrition.preparation_states(name, description) VALUES ('FRIED',    'DESCR_FRIED');
INSERT INTO nutrition.preparation_states(name, description) VALUES ('BAKED',    'DESCR_BAKED');
INSERT INTO nutrition.preparation_states(name, description) VALUES ('DRIED',    'DESCR_DRIED');

INSERT INTO nutrition.food_categories(name, description) VALUES ('FRUITS', 'Apple, banana, orange, strawberry');
INSERT INTO nutrition.food_categories(name, description) VALUES ('VEGETABLES', 'Zucchini, broccoli, carrot, spinach');
INSERT INTO nutrition.food_categories(name, description) VALUES ('MEAT_POULTRY', 'Beef, chicken, turkey, lamb');
INSERT INTO nutrition.food_categories(name, description) VALUES ('FISH_SEAFOOD', 'Salmon, tuna, shrimp, mussels');
INSERT INTO nutrition.food_categories(name, description) VALUES ('DAIRY_ALTERNATIVES', 'Milk, cheese, yogurt, soy milk');
INSERT INTO nutrition.food_categories(name, description) VALUES ('EGGS', 'Chicken eggs, quail eggs');
INSERT INTO nutrition.food_categories(name, description) VALUES ('GRAINS_CEREALS', 'Rice, oats, wheat, barley');
INSERT INTO nutrition.food_categories(name, description) VALUES ('PASTA_NOODLES', 'Udon, spaghetti, macaroni');
INSERT INTO nutrition.food_categories(name, description) VALUES ('LEGUMES', 'Lentils, beans, chickpeas, peas');
INSERT INTO nutrition.food_categories(name, description) VALUES ('NUTS_SEEDS', 'Almonds, walnuts, chia seeds');
INSERT INTO nutrition.food_categories(name, description) VALUES ('OILS_FATS', 'Olive oil, butter, sunflower oil');
INSERT INTO nutrition.food_categories(name, description) VALUES ('HERBS_SPICES', 'Parsley, basil, cumin, pepper');

INSERT INTO localization.locales (locale, name, description)
VALUES ('en', 'English', 'English'), ('tr', 'Turkish', 'Türkçe'), ('de', 'German', 'Deutsch');
    
