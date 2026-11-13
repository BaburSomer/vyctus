INSERT INTO auth.roles(name, description, created_by) VALUES ('ADMIN', 'Administrator of the application', 'INITIAL ADMIN');
INSERT INTO auth.roles(name, description, created_by) VALUES ('USER', 'An ordinary user of the application', 'INITIAL ADMIN');

INSERT INTO diary.meal_types(name, description, created_by) VALUES ('BREAKFAST', 'BREAKFAST', 'ADMIN');
INSERT INTO diary.meal_types(name, description, created_by) VALUES ('LUNCH', 'LUNCH', 'ADMIN');
INSERT INTO diary.meal_types(name, description, created_by) VALUES ('DINNER', 'DINNER', 'ADMIN');
INSERT INTO diary.meal_types(name, description, created_by) VALUES ('SNACK', 'SNACK', 'ADMIN');

INSERT INTO nutrition.units(name, description, created_by) VALUES ('MASS', 'MASS', 'ADMIN');
INSERT INTO nutrition.units(name, description, created_by) VALUES ('VOLUME', 'VOLUME', 'ADMIN');
INSERT INTO nutrition.units(name, description, created_by) VALUES ('COUNT', 'COUNT', 'ADMIN');
INSERT INTO nutrition.units(name, description, created_by) VALUES ('SERVING', 'SERVING', 'ADMIN');

INSERT INTO nutrition.preparation_states(name, description, created_by) VALUES ('RAW', 'RAW', 'ADMIN');
INSERT INTO nutrition.preparation_states(name, description, created_by) VALUES ('BOILED', 'BOILED', 'ADMIN');
INSERT INTO nutrition.preparation_states(name, description, created_by) VALUES ('GRILLED', 'GRILLED', 'ADMIN');
INSERT INTO nutrition.preparation_states(name, description, created_by) VALUES ('FRIED', 'FRIED', 'ADMIN');
INSERT INTO nutrition.preparation_states(name, description, created_by) VALUES ('BAKED', 'BAKED', 'ADMIN');
INSERT INTO nutrition.preparation_states(name, description, created_by) VALUES ('DRIED', 'DRIED', 'ADMIN');

INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Fruits', 'Apple, banana, orange, strawberry', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Vegetables', 'Zucchini, broccoli, carrot, spinach', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Meat & Poultry', 'Beef, chicken, turkey, lamb', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Fish & Seafood', 'Salmon, tuna, shrimp, mussels', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Dairy & Alternatives', 'Milk, cheese, yogurt, soy milk', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Eggs', 'Chicken eggs, quail eggs', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Grains & Cereals', 'Rice, oats, wheat, barley', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Pasta & Noodles', 'Udon, spaghetti, macaroni', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Legumes', 'Lentils, beans, chickpeas, peas', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Nuts & Seeds', 'Almonds, walnuts, chia seeds', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Oils & Fats', 'Olive oil, butter, sunflower oil', 'ADMIN');
INSERT INTO nutrition.food_categories(name, description, created_by) VALUES ('Herbs & Spices', 'Parsley, basil, cumin, pepper', 'ADMIN');