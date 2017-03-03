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
    public List<Restaurant> getAll(LocalDate date) {
        return repository.getAll(date);
    }
}
