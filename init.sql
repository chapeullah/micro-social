CREATE DATABASE authdb;
CREATE DATABASE profilesdb;
CREATE DATABASE postsdb;

CREATE USER auth_user WITH PASSWORD 'auth_pass';
CREATE USER profile_user WITH PASSWORD 'profile_pass';
CREATE USER posts_user WITH PASSWORD 'posts_pass';

GRANT ALL PRIVILEGES ON DATABASE authdb TO auth_user;
GRANT ALL PRIVILEGES ON DATABASE profilesdb TO profile_user;
GRANT ALL PRIVILEGES ON DATABASE postsdb TO posts_user;

\connect authdb
ALTER SCHEMA public OWNER TO auth_user;

\connect profilesdb
ALTER SCHEMA public OWNER TO profile_user;

\connect postsdb
ALTER SCHEMA public OWNER TO posts_user;