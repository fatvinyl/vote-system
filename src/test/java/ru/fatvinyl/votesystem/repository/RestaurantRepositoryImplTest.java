package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Restaurant;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.fatvinyl.votesystem.DishTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.*;

/**
 * @author Anton Yolgin
 */

public class RestaurantRepositoryImplTest extends AbstractRepositoryTest{


    @Autowired
    protected RestaurantRepository repository;

        @Test
    public void testSave() throws Exception  {
        Restaurant created = getCreated();
        repository.save(created);
            List<Restaurant> actual = repository.getAllByDate(TEST_DATE);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, created, RESTAURANT1), actual);
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2), repository.getAllByDate(TEST_DATE));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        Collection<Restaurant> actual = repository.getAllByDate(TEST_DATE);
        RESTAURANT_MATCHER.assertCollectionEquals(RESTAURANTS, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> actual = repository.getAll();
        System.out.println(123);
    }

    @Test
    public void testGetByMealDate() throws Exception {
        Restaurant actual = repository.getByMealDate(RESTAURANT1_ID, TEST_DATE);
        RESTAURANT_MATCHER.assertEquals(RESTAURANT1, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        repository.save(updated);
        RESTAURANT_MATCHER.assertEquals(updated, repository.getByMealDate(RESTAURANT1_ID, TEST_DATE));
    }



}