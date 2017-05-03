package ru.fatvinyl.votesystem.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = "/ajax/profile/restaurants")
public class RestaurantAjaxController extends AbstractRestaurantController {


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVote> getAllWIthDishesAndVotes() {
//        return super.getAllWIthDishesAndVotes(LocalDate.parse("2017-01-11", DateTimeUtil.DATE_FORMATTER));
        return super.getAllWIthDishesAndVotes(LocalDate.now());
    }

    @PostMapping(value = "/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVote> getAllFiltered(
            @RequestParam("menuDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate menuDate) {
        return super.getAllWIthDishesAndVotes(menuDate);
    }

    @Override
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant createOrUpdate(@Valid Restaurant restaurant) {
        if (restaurant.isNew()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, restaurant.getId());
        }
        return null;
    }

    @Override
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id")int id) {
        super.delete(id);
    }
}
