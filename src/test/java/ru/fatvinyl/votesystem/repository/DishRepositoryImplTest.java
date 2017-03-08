package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Dish;

import java.util.Collection;

import static org.junit.Assert.*;
import static ru.fatvinyl.votesystem.DishTestData.*;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;

/**
 * @author Anton Yolgin
 */
public class DishRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    protected DishRepository repository;

    @Test
    public void testGetAllByDate() throws Exception {
        DISHES_MATCHER.assertCollectionEquals(EXCEPTED_DISHES, repository.getAllByDate(TEST_DATE, RESTAURANT1_ID));
    }

    @Test
    public void testSave() throws Exception {
        Dish created = getCreated();
        repository.save(created, RESTAURANT1_ID);
        DISHES_MATCHER.assertEquals(repository.get(created.getId()), created);
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(CREATED_ID);
        DISHES_MATCHER.assertCollectionEquals(EXCEPTED_DISHES, repository.getAllByDate(TEST_DATE, RESTAURANT1_ID));

    }

    @Test
    public void testGet() throws Exception {
        Dish actual = repository.get(DISH1_ID);
        DISHES_MATCHER.assertEquals(DISH_1, actual);

    }

    @Test
    public void testUpdate() throws Exception {


    }


}