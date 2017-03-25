package ru.fatvinyl.votesystem.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;

import static ru.fatvinyl.votesystem.DishTestData.*;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT2_ID;

/**
 * @author Anton Yolgin
 */

public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;


    @Test
    public void testGet() throws Exception {
        Dish actual = service.get(DISH1_ID, RESTAURANT1_ID);
        DISHES_MATCHER.assertEquals(DISH_1, actual);
    }

    @Test
    public void testSave() throws Exception {
        Thread.sleep(1000);
        Dish created = getCreated();
        service.save(created, RESTAURANT1_ID);
        DISHES_MATCHER.assertEquals(service.get(created.getId(), RESTAURANT1_ID), created);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(CREATED_ID);
        DISHES_MATCHER.assertCollectionEquals(EXCEPTED_DISHES, service.getAllByDate(TEST_DATE, RESTAURANT1_ID));

    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(100);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish expected = getUpdated();
        Dish actual = service.update(expected, RESTAURANT1_ID);
        DISHES_MATCHER.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH1_ID);
        service.update(DISH_1, RESTAURANT2_ID);
    }
}