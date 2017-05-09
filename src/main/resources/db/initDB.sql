DROP TABLE
IF EXISTS user_roles;
DROP TABLE
IF EXISTS dishes;
DROP TABLE
IF EXISTS users;
DROP TABLE
IF EXISTS  votes;
DROP TABLE
IF EXISTS restaurants;


CREATE TABLE restaurants
(
  id  SERIAL PRIMARY KEY UNIQUE,
  name VARCHAR(30) NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx
  ON restaurants (name);

CREATE TABLE votes
(
  id            SERIAL PRIMARY KEY,
  amount        INTEGER DEFAULT 0,
  date          DATE    DEFAULT now(),
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_idx
  ON votes (date, restaurant_id);

CREATE TABLE dishes
(
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(50) NOT NULL,
  price         VARCHAR(8)  NOT NULL,
  date          DATE DEFAULT now(),
  restaurant_id INTEGER     NOT NULL,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);


CREATE TABLE users
(
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(30) NOT NULL,
  email         VARCHAR(50) NOT NULL,
  password      VARCHAR     NOT NULL,
  registered    TIMESTAMP DEFAULT now(),
  enabled       BOOLEAN   DEFAULT TRUE,
  vote_id INTEGER   DEFAULT NULL,
  FOREIGN KEY (vote_id) REFERENCES votes (id)
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

