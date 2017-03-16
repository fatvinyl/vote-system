package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface RestaurantService {

    List<RestaurantWithVote> getAllWIthDishesAndVotes(LocalDate date);

    List<Restaurant> getAll();

    Restaurant getByDate(int id, LocalDate mealDate);

    Restaurant update(Restaurant restaurant);

    Restaurant save(Restaurant restaurant);

    void delete(int id);
}
