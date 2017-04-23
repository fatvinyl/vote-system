package ru.fatvinyl.votesystem.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

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

    @PostMapping(value = "/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVote> getAllFiltered(
            @RequestParam("menuDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate menuDate) {
        return super.getAllWIthDishesAndVotes(menuDate);
    }

//    @PostMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<RestaurantWithVote> getAllFiltered(
//            @RequestParam(value = "menuDate", required = false) LocalDate date) {
//        return super.getAllWIthDishesAndVotes(date);
//    }

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
