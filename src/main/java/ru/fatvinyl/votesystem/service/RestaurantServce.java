package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface RestaurantServce {

    List<Restaurant> getAllWIthVotesAndDishes(LocalDate date);

    List<Restaurant> getAll();

    Restaurant getByDate(int id, LocalDate mealDate);

    Restaurant update(Restaurant restaurant);

    Restaurant save(Restaurant restaurant);

    void delete(int id);
}
