package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Anton Yolgin
 */

@Service
public class RestaurantServceImpl implements RestaurantServce {

    @Autowired
    private RestaurantRepository repository;


    @Override
    public List<Restaurant> getAllWIthVotesAndDishes(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getAllWIthVotesAndDishes(date);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant getByDate(int id, LocalDate mealDate) {
        Assert.notNull(mealDate, "mealDate must not be null");
        return repository.getByDate(id, mealDate);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
