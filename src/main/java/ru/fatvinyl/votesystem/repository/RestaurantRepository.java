package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Restaurant;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Anton Yolgin
 */

public interface RestaurantRepository {

    List<Restaurant> getAll(LocalDate date);
}
