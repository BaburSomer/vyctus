ALTER TABLE auth.user_preferences
    ADD CONSTRAINT idx_user_preferences
    UNIQUE (user_oid, locale);