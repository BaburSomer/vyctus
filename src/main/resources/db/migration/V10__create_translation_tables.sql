CREATE TABLE localization.translations (
    key         varchar(100) NOT NULL,
    locale      varchar(20)  NOT NULL,
    translation text         NOT NULL,

    PRIMARY KEY (key, locale),
    FOREIGN KEY (locale) REFERENCES localization.locales(locale)
);