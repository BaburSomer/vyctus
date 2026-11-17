-- constaint adını bulma 
-- SELECT constraint_name
-- FROM information_schema.table_constraints
-- WHERE table_schema = 'auth'
--   AND table_name = 'user_roles'
--   AND constraint_type = 'PRIMARY KEY';

ALTER TABLE auth.user_roles DROP CONSTRAINT user_roles_pkey;
ALTER TABLE auth.user_roles ADD oid UUID PRIMARY KEY DEFAULT gen_random_uuid();