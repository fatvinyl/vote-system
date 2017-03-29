package ru.fatvinyl.votesystem.web.vote;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.service.VoteService;
import ru.fatvinyl.votesystem.web.AbstractControllerTest;
import ru.fatvinyl.votesystem.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.fatvinyl.votesystem.VoteTestData.*;

/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    VoteService service;

    @Test
    public void test1Create() throws Exception {
        Vote created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + RESTAURANT1_ID + "&10:59:59")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Vote returned = VOTE_MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        VOTE_MATCHER.assertEquals(created, returned);
    }

    @Test
    public void test2Increment() throws Exception {
        Vote vote = new Vote(VOTE_ID_CREATED, 1, LocalDate.now(), RESTAURANT1_ID);
        mockMvc.perform(put(REST_URL + "increment/" + VOTE_ID_CREATED + "&10:59:59").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(vote)))
                .andExpect(status().isOk());

        VOTE_MATCHER.assertEquals(getIncremented(), service.get(VOTE_ID_CREATED));
    }

    @Test
    public void test3Decrement() throws Exception {
        Vote vote = getIncremented();
        mockMvc.perform(put(REST_URL + "decrement/" + VOTE_ID_CREATED + "&10:59:59").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(vote)))
                .andExpect(status().isOk());
        Vote expected = new Vote(VOTE_ID_CREATED, 1, LocalDate.now(), RESTAURANT1_ID);
        VOTE_MATCHER.assertEquals(expected, service.get(VOTE_ID_CREATED));
    }

    @Test
    public void test4Get() throws Exception {
        Vote expected = new Vote(VOTE_ID_CREATED, 1, LocalDate.now(), RESTAURANT1_ID);
        VOTE_MATCHER.assertEquals(expected, service.get(VOTE_ID_CREATED));
    }

}