\c uml_db

BEGIN;
CREATE TABLE rules (
  id   SERIAL PRIMARY KEY,
  name TEXT
);
CREATE TABLE roles (
  id   SERIAL PRIMARY KEY,
  name TEXT
);
CREATE TABLE roles_to_rules (
  id      SERIAL PRIMARY KEY,
  role_id INT REFERENCES roles (id),
  rule_id INT REFERENCES rules (id)
);
CREATE TABLE states(
        id serial primary key,
        name text
);
CREATE TABLE categories(
        id serial primary key,
        name text
);
CREATE TABLE users(
        id serial primary key,
        name text,
        role_id int REFERENCES roles(id)
);
CREATE TABLE items(
	id serial primary key,
	name text,
	state_id int REFERENCES states(id),
	category_id int REFERENCES categories(id),
	user_id int REFERENCES users(id)
);
CREATE TABLE comments(
	id serial primary key,
	comment text,
	item_id int REFERENCES items(id)
);
CREATE TABLE attachs(
	id serial primary key,
	file_name text,
	item_id int REFERENCES items(id)
);
COMMIT;

\i create_data.sql
