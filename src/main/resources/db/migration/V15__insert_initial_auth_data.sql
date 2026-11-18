
INSERT INTO auth.users (first_name, last_name, birthdate, height, created_by) VALUES ('Babür', 'Somer', '19.12.1964', 1.68, 'ADMIN');
INSERT INTO auth.users (first_name, last_name, birthdate, height, created_by) VALUES ('Elif', 'Somer', '05.03.1967', 1.63, 'ADMIN');
INSERT INTO auth.users (first_name, last_name, birthdate, height, created_by) VALUES ('Administrator', '---', '01.01.1970', 0.0, 'ADMIN');


INSERT INTO auth.user_roles (user_oid, role_oid, created_by) VALUES ((SELECT oid from auth.users WHERE first_name = 'Administrator'), 
                                                                     (SELECT oid from auth.roles WHERE name = 'ADMIN'), 'ADMIN');
INSERT INTO auth.user_roles (user_oid, role_oid, created_by) VALUES ((SELECT oid from auth.users WHERE first_name = 'Babür'), 
                                                                     (SELECT oid from auth.roles WHERE name = 'ADMIN'), 'ADMIN');
INSERT INTO auth.user_roles (user_oid, role_oid, created_by) VALUES ((SELECT oid from auth.users WHERE first_name = 'Babür'), 
                                                                     (SELECT oid from auth.roles WHERE name = 'USER'), 'USER');
INSERT INTO auth.user_roles (user_oid, role_oid, created_by) VALUES ((SELECT oid from auth.users WHERE first_name = 'Elif'), 
                                                                     (SELECT oid from auth.roles WHERE name = 'USER'), 'ADMIN');

INSERT INTO auth.user_preferences(user_oid, locale, timezone, measurement_system, energy_unit, created_by)
                    VALUES ((SELECT oid from auth.users WHERE first_name = 'Babür'), 
                            (SELECT locale from localization.locales WHERE name = 'English'), 'GMT+0', 'imperial', 'kJ', 'ADMIN');
INSERT INTO auth.user_preferences(user_oid, locale, timezone, measurement_system, energy_unit, created_by)
                    VALUES ((SELECT oid from auth.users WHERE first_name = 'Babür'), 
                            (SELECT locale from localization.locales WHERE name = 'Turkish'), 'GMT+3', 'metric', 'kcal', 'ADMIN');
INSERT INTO auth.user_preferences(user_oid, locale, timezone, measurement_system, energy_unit, created_by)
                    VALUES ((SELECT oid from auth.users WHERE first_name = 'Elif'), 
                            (SELECT locale from localization.locales WHERE name = 'Turkish'), 'GMT+3', 'metric', 'kcal', 'ADMIN');
INSERT INTO auth.user_preferences(user_oid, locale, timezone, measurement_system, energy_unit, created_by)
                    VALUES ((SELECT oid from auth.users WHERE first_name = 'Elif'), 
                            (SELECT locale from localization.locales WHERE name = 'German'), 'GMT+1', 'metric', 'kcal', 'ADMIN');