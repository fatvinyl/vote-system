package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */
@Service
public class RestaurantServceImpl implements RestaurantServce {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public List<Restaurant> getAllWIthVotesAndDishes(LocalDate date) {
        return repository.getAllWIthVotesAndDishes(date);
    }

    @Override
    public Restaurant get(int id, LocalDate mealDate) {
        return repository.getByMealDate(id, mealDate);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }


}
