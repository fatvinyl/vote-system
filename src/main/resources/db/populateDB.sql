

INSERT INTO restaurants (name) VALUES
  ('Шашлыкофф'),
  ('Макдоналдс'),
  ('Лаундж');

INSERT INTO users (name, email, password) VALUES
 ('Admin', 'admin@mail.com' , '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju'),
 ('User_1', 'user1@mail.com', '$2a$10$seI9HiMpF0rR7povEjG.MOi62z9zGIHyY8XsBB6HZTKMxwtbgtH6.'),
 ('User_2', 'user2@mail.com', '$2a$10$A1Nev/cAM3mXyEQe5DOyS.NKZmCba.z6Ko3X6n1lEMRvnXfIJbOse');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 1),
  ('ROLE_USER', 1),
  ('ROLE_USER', 2),
  ('ROLE_USER', 3);

INSERT INTO dishes (name, price, restaurant_id) VALUES
  ('Картофель жареный', '100,00', 1),
  ('Шашлык', '150,00', 1),
  ('Чай', '50,00', 1),
  ('Картофель фри', '70,00', 2),
  ('Куриные крылышки', '120,00', 2),
  ('Напиток газированый', '30,00',  2),
  ('Суп грибной', '100,00',  3),
  ('Мясо по уюгурски', '220,00', 3),
  ('Кофе', '120,00', 3);

INSERT INTO dishes (name, price, date, restaurant_id) VALUES
  ('Тестовая еда 1', '100,00', '2017-01-11', 1),
  ('Тестовая еда 2', '150,00', '2017-01-11', 1),
  ('Тестовая еда 3', '50,00', '2017-01-11', 2),
  ('Тестовая еда 4', '70,00', '2017-01-11', 2),
  ('Тестовая еда 5', '120,00', '2017-01-11', 3),
  ('Тестовая еда 6', '30,00', '2017-01-11', 3);

INSERT INTO votes (amount, date, restaurant_id) VALUES
  (3, '2017-01-11', 1),
  (4, '2017-01-11', 2),
  (5, '2017-01-11', 3);

-- INSERT INTO votes (amount, restaurant_id) VALUES
--   (3, 2);
