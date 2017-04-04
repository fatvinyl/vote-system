package ru.fatvinyl.votesystem.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = "/ajax/profile/restaurants")
public class RestaurantAjaxController extends AbstractRestaurantController {


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<RestaurantWithVote> getAllWIthDishesAndVotes() {
//        return super.getAllWIthDishesAndVotes(LocalDate.parse("2017-01-11", DateTimeUtil.DATE_FORMATTER));
        return super.getAllWIthDishesAndVotes(LocalDate.now());
    }

    @Override
    List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    Restaurant update(Restaurant restaurant, int id) {
        return super.update(restaurant, id);
    }

    @Override
    Restaurant create(Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    void delete(int id) {
        super.delete(id);
    }
}
