CREATE DATABASE authdb;
CREATE DATABASE profiledb;
CREATE DATABASE postdb;
CREATE DATABASE socialdb;

CREATE USER auth_user WITH PASSWORD 'auth_pass';
CREATE USER profile_user WITH PASSWORD 'profile_pass';
CREATE USER post_user WITH PASSWORD 'post_pass';
CREATE USER social_user WITH PASSWORD 'social_pass';

GRANT ALL PRIVILEGES ON DATABASE authdb TO auth_user;
GRANT ALL PRIVILEGES ON DATABASE profiledb TO profile_user;
GRANT ALL PRIVILEGES ON DATABASE postdb TO post_user;
GRANT ALL PRIVILEGES ON DATABASE socialdb TO social_user;

\connect authdb
ALTER SCHEMA public OWNER TO auth_user;

\connect profiledb
ALTER SCHEMA public OWNER TO profile_user;

\connect postdb
ALTER SCHEMA public OWNER TO post_user;
`1`
\connect socialdb
ALTER SCHEMA public OWNER TO social_user;