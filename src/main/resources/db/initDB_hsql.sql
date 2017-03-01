DROP TABLE user_roles
IF EXISTS;
DROP TABLE meals
IF EXISTS;
DROP TABLE restaurants
IF EXISTS;
DROP TABLE users
IF EXISTS;

CREATE TABLE restaurants
(
  id           INTEGER IDENTITY PRIMARY KEY,
  name         VARCHAR(30) NOT NULL,
  amount_votes INTEGER DEFAULT 0
);

CREATE TABLE users
(
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30) NOT NULL,
  email      VARCHAR(50) NOT NULL,
  password   VARCHAR(50) NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOLEAN   DEFAULT TRUE,
  vote_id    INTEGER   DEFAULT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(20),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE meals
(
  id            INTEGER IDENTITY PRIMARY KEY,
  name          VARCHAR(50) NOT NULL,
  price         VARCHAR(8)  NOT NULL,
  date          DATE   NOT NULL,
  restaurant_id INTEGER     NOT NULL,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);
CREATE INDEX meals_unique_name_idx
  ON meals (restaurant_id, name)