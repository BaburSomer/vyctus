ALTER TABLE diary.meals
    ADD column total_calories decimal(6,2) NOT NULL;

ALTER TABLE diary.meal_items
    ADD column calories decimal(6,2) NOT NULL;