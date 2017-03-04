package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Restaurant;


import java.util.Arrays;
import java.util.Collection;

import static ru.fatvinyl.votesystem.MealTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.*;

/**
 * @author Anton Yolgin
 */

public class RestaurantRepositoryImplTest extends AbstractRepositoryTest{


    @Autowired
    protected RestaurantRepository repository;

    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> actual = repository.getAll(TEST_DATE);
        RESTAURANT_MATCHER.assertCollectionEquals(RESTAURANTS, actual);
    }

    @Test
    public void testGetById() throws Exception {
        Restaurant actual = repository.get(RESTAURANT1_ID, TEST_DATE);
        RESTAURANT_MATCHER.assertEquals(RESTAURANT1, actual);
    }

    //    @Test
//    public void testSave() throws Exception  {
//        Restaurant created = getCreated();
//        repository.save(created);
//        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, created, RESTAURANT1), repository.getAll(TEST_DATE));
//    }
    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        repository.save(updated);
        RESTAURANT_MATCHER.assertEquals(updated, repository.get(RESTAURANT1_ID, TEST_DATE));
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2), repository.getAll(TEST_DATE));
    }

}