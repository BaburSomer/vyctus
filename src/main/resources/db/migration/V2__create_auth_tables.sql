CREATE TABLE IF NOT EXISTS auth.users (
    oid uuid NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    birthdate date NOT NULL,
    height decimal(5,2) NOT NULL,
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_users_1 ON auth.users (oid);
CREATE UNIQUE INDEX idx_users_2 ON auth.users (first_name, last_name);


CREATE TABLE IF NOT EXISTS auth.roles (
    oid uuid NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    name varchar(50) NOT NULL UNIQUE,
    description text,
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (oid)
);
CREATE UNIQUE INDEX idx_roles_1 ON auth.roles (oid);


CREATE TABLE IF NOT EXISTS auth.user_roles (
    user_oid uuid NOT NULL,
    role_oid uuid NOT NULL,
    created timestamp without time zone NOT NULL DEFAULT now(),
    created_by varchar(50) NOT NULL,
    updated timestamp without time zone,
    updated_by varchar(50),
    version int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    PRIMARY KEY (user_oid, role_oid)
);

ALTER TABLE auth.user_roles ADD CONSTRAINT fk_user_roles_user_oid FOREIGN KEY (user_oid) REFERENCES auth.users (oid);
ALTER TABLE auth.user_roles ADD CONSTRAINT fk_user_roles_role_oid FOREIGN KEY (role_oid) REFERENCES auth.roles (oid);
CREATE UNIQUE INDEX idx_user_roles_1 ON auth.user_roles (user_oid, role_oid);
