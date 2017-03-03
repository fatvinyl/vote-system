package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface RestaurantServce {

    List<Restaurant> getAll(LocalDate date);
}
