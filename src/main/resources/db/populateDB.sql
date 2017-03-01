-- DELETE FROM users;
-- DELETE FROM restaurants;
-- DELETE FROM user_roles;
-- DELETE FROM meals;

INSERT INTO restaurants (name) VALUES
  ('Шашлыкофф'),
  ('Макдоналдс'),
  ('Лаундж');

INSERT INTO users (name, email, password) VALUES
 ('Admin', 'admin@mail.com' , 'admin'),
 ('User_1', 'user1@mail.com', 'password_1'),
 ('User_2', 'user2@mail.com', 'password_2');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 0),
  ('ROLE_USER', 1),
  ('ROLE_USER', 2);

INSERT INTO meals (name, price, date, restaurant_id) VALUES
  ('Картофель жареный', '100,00', '2017-01-11', 0),
  ('Шашлык', '150,00', '2016-01-11', 0),
  ('Чай', '50,00', '2017-01-11', 0),
  ('Картофель фри', '70,00', '2017-01-11', 1),
  ('Куриные крылышки', '120,00', '2016-01-11', 1),
  ('Напиток газированый', '30,00', '2016-01-11', 1),
  ('Суп грибной', '100,00', '2017-01-11', 2),
  ('Мясо по уюгурски', '220,00', '2017-01-11', 2),
  ('Кофе', '120,00', '2017-01-11', 2);

