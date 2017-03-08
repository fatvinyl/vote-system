package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;

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
        Vote expected = getCreated();
        Vote actual = repository.create(RESTAURANT1_ID);
        VOTE_MATCHER.assertEquals(expected, actual);
    }

    @Test
    public void increment() throws Exception {
        repository.increment(LocalDate.now(),RESTAURANT1_ID);
        Vote expected = getIncremented();
        Vote actual = repository.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(expected, actual);
    }

    @Test
    public void decrement() throws Exception {
        repository.decrement(LocalDate.now(),RESTAURANT1_ID);
        Vote expected = getCreated();
        Vote actual = repository.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(expected, actual);
    }

}