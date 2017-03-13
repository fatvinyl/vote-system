package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Anton Yolgin
 */

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }

    @Override
    public Dish update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Dish> getAllByDate(LocalDate date, int restaurantId) {
        Assert.notNull(date, "date must not be null");
        return repository.getAllByDate(date, restaurantId);
    }
}
