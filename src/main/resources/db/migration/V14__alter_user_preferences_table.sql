ALTER TABLE auth.user_preferences
    DROP CONSTRAINT IF EXISTS user_preferences_pkey;

ALTER TABLE auth.user_preferences
    ADD CONSTRAINT user_preferences_pkey
    PRIMARY KEY (user_oid, locale);