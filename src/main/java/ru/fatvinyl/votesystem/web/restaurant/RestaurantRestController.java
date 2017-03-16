package ru.fatvinyl.votesystem.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController{
    static final String REST_URL ="/rest/restaurants";


    @Override
    @GetMapping(value = "/{date}")
    List<RestaurantWithVote> getAllWIthDishesAndVotes(LocalDate date) {
        return super.getAllWIthDishesAndVotes(date);
    }

    @Override
    @GetMapping
    List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Restaurant update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        return super.update(restaurant, id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
