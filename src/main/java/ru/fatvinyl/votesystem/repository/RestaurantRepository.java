package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import java.time.LocalDate;
import java.util.List;


/**
 * @author Anton Yolgin
 */

public interface RestaurantRepository {

    List<Restaurant> getAllWIthDishes(LocalDate date);

    List<RestaurantWithVote> getAllWIthDishesAndVotes(LocalDate date);

    List<Restaurant> getAll();

    Restaurant getByDate(int id, LocalDate mealDate);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

}
