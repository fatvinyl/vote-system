package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Meal;

import java.time.LocalDate;
import java.util.Collection;

/**
 *
 *
 * @author Anton Yolgin
 */
public interface MealRepository {

    Meal save(Meal meal, int restaurantId);

    boolean delete(int id, int restaurantId);

    Meal get(int id, int restaurantId);

    Collection<Meal> getAll();

    Collection<Meal> getByDate(LocalDate date);
}
