package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Vote;


import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.fatvinyl.votesystem.VoteTestData.*;

/**
 * @author Anton Yolgin
 */
public class VoteRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    public VoteRepository repository;

    @Test
    public void create() throws Exception {
        Vote actual = repository.create(RESTAURANT1_ID, 1);
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, actual);
        //add users matcher
    }

    @Test
    public void increment() throws Exception {
        repository.update(VOTE_ID_CREATED, 2);
        Vote expected = getIncremented();
        Vote actual = repository.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(expected, actual);
        //add users matcher
    }

    @Test
    public void decrement() throws Exception {
        repository.delete(VOTE_ID_CREATED, 2);
        Vote actual = repository.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, actual);
        //add users matcher
    }

}