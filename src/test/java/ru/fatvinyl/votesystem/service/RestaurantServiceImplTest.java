package ru.fatvinyl.votesystem.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;


import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static ru.fatvinyl.votesystem.DishTestData.DISH_1;
import static ru.fatvinyl.votesystem.DishTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.*;
import static ru.fatvinyl.votesystem.VoteTestData.VOTES;
import static ru.fatvinyl.votesystem.util.RestaurantUtil.getWithVote;

/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantServiceImplTest extends AbstractServiceTest {


    @Autowired
    protected RestaurantService service;

    @Test
    public void test1Save() throws Exception {
        Restaurant created = getCreated();
        service.save(created);
        List<Restaurant> actual = service.getAll();
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, created, RESTAURANT1), actual);
    }

    @Test
    public void test2Delete() throws Exception {
        service.delete(RESTAURANT_CREATED_ID);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, RESTAURANT1), service.getAll());
    }

    @Test
    public void test3DeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(10);
    }

    @Test
    public void test4GetAllWithDishesAndVotes() throws Exception {
        List<RestaurantWithVote> actual = service.getAllWIthDishesAndVotes(TEST_DATE);
        List<RestaurantWithVote> expected = getWithVote(RESTAURANTS, VOTES);
        RESTAURANT_WITH_VOTE_MATCHER.assertCollectionEquals(actual, expected);
    }

    @Test
    public void test5GetAll() throws Exception {
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, RESTAURANT1), service.getAll());
    }

    @Test
    public void test6GetByDate() throws Exception {
        Restaurant actual = service.getByDate(RESTAURANT1_ID, TEST_DATE);
        RESTAURANT_MATCHER.assertEquals(RESTAURANT1, actual);
    }

    @Test
    public void test7Update() throws Exception {
        Restaurant updated = getUpdated();
        service.save(updated);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, updated), service.getAll());
    }

    @Test
    public void test8Validation() throws Exception {
        validateRootCause(() -> service.save(new Restaurant(null, " ", DISH_1)), ConstraintViolationException.class);
    }
}