package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface DishService {

    Dish save(Dish dish, int restaurantId);

    Dish update(Dish dish, int restaurantId);

    void delete(int id);

    Dish get(int id);

    Dish get(int id, int restaurantId);

    List<Dish> getAllByDate(LocalDate date, int restaurantId);

}
