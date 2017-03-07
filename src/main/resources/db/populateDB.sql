-- DELETE FROM users;
-- DELETE FROM restaurants;
-- DELETE FROM user_roles;
-- DELETE FROM dishes;

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

INSERT INTO dishes (name, price, restaurant_id) VALUES
  ('Картофель жареный', '100,00', 0),
  ('Шашлык', '150,00', 0),
  ('Чай', '50,00', 0),
  ('Картофель фри', '70,00', 1),
  ('Куриные крылышки', '120,00', 1),
  ('Напиток газированый', '30,00',  1),
  ('Суп грибной', '100,00',  2),
  ('Мясо по уюгурски', '220,00', 2),
  ('Кофе', '120,00', 2);

INSERT INTO dishes (name, price, date, restaurant_id) VALUES
  ('Тестовая еда 1', '100,00', '2017-01-11', 0),
  ('Тестовая еда 2', '150,00', '2017-01-11', 0),
  ('Тестовая еда 3', '50,00', '2017-01-11', 1),
  ('Тестовая еда 4', '70,00', '2017-01-11', 1),
  ('Тестовая еда 5', '120,00', '2017-01-11', 2),
  ('Тестовая еда 6', '30,00', '2017-01-11', 2);

INSERT INTO votes (amount, date, restaurant_id) VALUES
  (2, '2017-01-11', 0),
  (3, '2017-01-11', 1),
  (4, '2017-01-11', 2);