package ru.fatvinyl.votesystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.repository.VoteRepository;


import java.util.Arrays;
import java.util.List;

import static ru.fatvinyl.votesystem.DishTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.fatvinyl.votesystem.VoteTestData.*;

/**
 * @author Anton Yolgin
 */
public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    public VoteRepository repository;

    @Test
    public void testSave() throws Exception {
        Vote actual = repository.save(RESTAURANT1_ID, 1);
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, actual);
        //add users matcher
    }

    @Test
    public void testIncrement() throws Exception {
        repository.update(VOTE_ID_CREATED, 2);
        Vote expected = getIncremented();
        Vote actual = repository.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(expected, actual);
        //add users matcher
    }

    @Test
    public void testDecrement() throws Exception {
        repository.delete(VOTE_ID_CREATED, 2);
        Vote actual = repository.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, actual);
        //add users matcher
    }

    @Test
    public void testGetAllByDAte() throws Exception {
        List<Vote> expected = repository.getAllByDate(TEST_DATE);
        List<Vote> actual = Arrays.asList(VOTE1, VOTE2, VOTE3);
        VOTE_MATCHER.assertCollectionEquals(expected, actual);
        //add users matcher
    }

}