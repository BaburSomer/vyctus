-- Add a UUID identifier to existing user_preferences records
ALTER TABLE auth.user_preferences
    ADD COLUMN oid UUID;

-- Generate a unique UUID for every existing record
UPDATE auth.user_preferences
SET oid = gen_random_uuid()
WHERE oid IS NULL;

-- Make the identifier mandatory
ALTER TABLE auth.user_preferences
    ALTER COLUMN oid SET NOT NULL;

-- Delete old primary key
ALTER TABLE auth.user_preferences DROP CONSTRAINT user_preferences_pkey;

-- Define the primary key
ALTER TABLE auth.user_preferences
    ADD CONSTRAINT user_preferences_pkey PRIMARY KEY (oid);