package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.repository.RestaurantRepository;
import ru.fatvinyl.votesystem.repository.VoteRepository;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import java.time.LocalDate;
import java.util.List;

import static ru.fatvinyl.votesystem.util.RestaurantUtil.getWithVote;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Anton Yolgin
 */

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public List<RestaurantWithVote> getAllWIthDishesAndVotes(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return  getWithVote(repository.getAllWIthDishes(date), voteRepository.getAllByDate(date));
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant getByDate(int id, LocalDate date) {
        Assert.notNull(date, "mealDate must not be null");
        return repository.getByDate(id, date);
    }

    @Transactional
    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
