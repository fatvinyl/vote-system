package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Meal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 *
 *
 * @author Anton Yolgin
 */
public interface MealRepository {

    Meal save(Meal meal, int restaurantId);

    boolean delete(int id);

    Meal get(int id);

}
