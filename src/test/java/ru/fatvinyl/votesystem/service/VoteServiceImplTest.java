package ru.fatvinyl.votesystem.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;


import java.time.LocalDate;

import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.fatvinyl.votesystem.VoteTestData.*;

/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    public VoteService service;

    @Test
    public void test1Save() throws Exception {
        Vote actual = service.save(getCreated(), 1);
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, actual);
        //add users matcher
    }

    @Test
    public void test2Get() throws Exception {
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, service.get(VOTE_ID_CREATED));
    }

    @Test
    public void test3Update() throws Exception {
        Vote actual = service.update(new Vote(VOTE_ID_CREATED, 1, LocalDate.now(), RESTAURANT1_ID), 1);
        VOTE_MATCHER.assertEquals(getIncremented(), actual);
        //add users matcher
    }

    @Test
    public void test4Delete() throws Exception {
        service.delete(VOTE_ID_CREATED, 1);
        Vote actual = service.get(VOTE_ID_CREATED);
        VOTE_MATCHER.assertEquals(VOTE_EXPECTED, actual);
        //add users matcher
    }

    @Test
    public void test5GetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + 100);
        service.get(100);
    }

}