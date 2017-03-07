package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Dish;

/**
 *
 *
 * @author Anton Yolgin
 */
public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id);

    Dish get(int id);

}
