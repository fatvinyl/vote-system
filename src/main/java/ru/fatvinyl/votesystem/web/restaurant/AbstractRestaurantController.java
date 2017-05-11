package ru.fatvinyl.votesystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.service.RestaurantService;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import java.time.LocalDate;
import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkIdConsistent;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNew;

/**
 * @author Anton Yolgin
 */

public abstract class AbstractRestaurantController  {
    private static Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    RestaurantService service;

    List<RestaurantWithVote> getAllWIthDishesAndVotes(LocalDate date) {
        LOG.info("get all restaurants with dishes and votes for date {}", date);
        return service.getAllWIthDishesAndVotes(date);
    }

    List<Restaurant> getAll() {
        LOG.info("get all restaurants");
        return service.getAll();
    }

    Restaurant update(Restaurant restaurant, int id) {
        checkIdConsistent(restaurant, id);
        LOG.info("update {}", restaurant);
        return service.update(restaurant);
    }

    Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        LOG.info("create {}", restaurant);
        return service.save(restaurant);
    }

    void delete(int id) {
        LOG.info("delete restaurant {}", id);
        service.delete(id);
    }
}
