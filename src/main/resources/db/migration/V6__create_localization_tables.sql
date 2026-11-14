CREATE TABLE IF NOT EXISTS localization.locales (
    locale varchar(20) NOT NULL UNIQUE,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    fallback_locale varchar(20),
    active boolean NOT NULL DEFAULT true,
    PRIMARY KEY (locale)
);
CREATE UNIQUE INDEX idx_locales_1 ON localization.locales (locale);
ALTER TABLE localization.locales ADD CONSTRAINT locales_fk1 FOREIGN KEY (fallback_locale) REFERENCES localization.locales(locale);
