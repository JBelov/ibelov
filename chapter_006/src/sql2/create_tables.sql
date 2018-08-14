\c cars_db

BEGIN;
CREATE TABLE body (
  id   SERIAL PRIMARY KEY,
  name TEXT
);
CREATE TABLE engine (
  id   SERIAL PRIMARY KEY,
  name TEXT
);
CREATE TABLE transmission (
  id   SERIAL PRIMARY KEY,
  name TEXT
);
CREATE TABLE car (
  id           SERIAL PRIMARY KEY,
  name         TEXT,
  body         INT NOT NULL REFERENCES body (id),
  engine       INT NOT NULL REFERENCES engine (id),
  transmission INT NOT NULL REFERENCES transmission (id)
);
COMMIT;

\i create_data.sql
