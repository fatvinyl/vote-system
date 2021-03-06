package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 *
 *
 * @author Anton Yolgin
 */
public interface DishDAO {

    Dish get(int id);

    List<Dish> getAllByDate(LocalDate date, int restaurantId);

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id);


}
