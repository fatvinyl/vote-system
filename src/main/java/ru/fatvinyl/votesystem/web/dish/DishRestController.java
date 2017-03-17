package ru.fatvinyl.votesystem.web.dish;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fatvinyl.votesystem.model.Dish;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends AbstractDishController {
    static final String REST_URL = "rest/dishes";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Dish> createWIthLocation(@RequestBody Dish dish, @RequestBody int restaurantId) {
        Dish created = super.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Dish update(@RequestBody Dish dish, @PathVariable("id") int id, @RequestBody int restaurantId) {
        return super.update(dish, id, restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    Dish get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    List<Dish> getAllByDate(@RequestParam(value = "date") LocalDate date,
                            @RequestParam(value = "restaurantId") int restaurantId) {
        return super.getAllByDate(date, restaurantId);
    }
}
