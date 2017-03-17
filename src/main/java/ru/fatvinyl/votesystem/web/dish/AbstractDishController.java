package ru.fatvinyl.votesystem.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.service.DishService;

import java.time.LocalDate;
import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkIdConsistent;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNew;

/**
 * @author Anton Yolgin
 */

public abstract class AbstractDishController {
    private static Logger LOG = LoggerFactory.getLogger(AbstractDishController.class);

    @Autowired
    private DishService service;

    Dish create(Dish dish, int restaurantId) {
        checkNew(dish);
        LOG.info("create {} for Restaurant {}", dish, restaurantId);
        return service.save(dish, restaurantId);
    }

    Dish update(Dish dish, int id, int restaurantId) {
        checkIdConsistent(dish, id);
        LOG.info("update {} for Restaurant {}", dish, restaurantId);
        return service.update(dish, restaurantId);
    }

    void delete(int id) {
        LOG.info("delete dish {}", id);
        service.delete(id);
    }

    Dish get(int id) {
        LOG.info("get dish {}");
        return service.get(id);
    }

    List<Dish> getAllByDate(LocalDate date, int restaurantId) {
        LOG.info("get all dishes for restaurant {}, for date {}", restaurantId, date);
        return service.getAllByDate(date, restaurantId);
    }
}
