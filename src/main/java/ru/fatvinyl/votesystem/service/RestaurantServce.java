package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface RestaurantServce {

    List<Restaurant> getAllWIthVotesAndDishes(LocalDate date);

    Restaurant get(int id, LocalDate mealDate);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

}
