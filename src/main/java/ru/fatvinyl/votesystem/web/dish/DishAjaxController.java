package ru.fatvinyl.votesystem.web.dish;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.fatvinyl.votesystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = "/ajax/profile/dishes")
public class DishAjaxController extends AbstractDishController {

    @Override
    Dish create(Dish dish, int restaurantId) {
        return super.create(dish, restaurantId);
    }

    @Override
    Dish update(Dish dish, int id, int restaurantId) {
        return super.update(dish, id, restaurantId);
    }

    @Override
    void delete(int id) {
        super.delete(id);
    }

    @Override
    Dish get(int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/{date}&{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByDate(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
            @PathVariable("restaurantId") int restaurantId) {
        return super.getAllByDate(date, restaurantId);
    }
}
