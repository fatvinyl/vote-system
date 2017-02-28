package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Meal;
import ru.fatvinyl.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

/**
 * @author Anton Yolgin
 */

public interface RestaurantRepository {

    Map<Restaurant, Collection<Meal>> getAll(LocalDate date);
}
