CREATE TABLE auth.user_preferences (
    user_oid uuid PRIMARY KEY,
    locale varchar(20) NOT NULL DEFAULT 'en',
    timezone varchar(100) DEFAULT 'UTC',
    measurement_system varchar(20) NOT NULL DEFAULT 'METRIC',
    energy_unit varchar(10) NOT NULL DEFAULT 'KCAL',
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),

    CONSTRAINT fk_user_preferences_user
        FOREIGN KEY (user_oid)
        REFERENCES auth.users(oid),

    CONSTRAINT fk_user_preferences_locale
        FOREIGN KEY (locale)
        REFERENCES localization.locales(locale)
);