package ru.fatvinyl.votesystem.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.service.RestaurantService;
import ru.fatvinyl.votesystem.util.RestaurantUtil;
import ru.fatvinyl.votesystem.web.AbstractControllerTest;
import ru.fatvinyl.votesystem.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fatvinyl.votesystem.DishTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.*;
import static ru.fatvinyl.votesystem.VoteTestData.VOTES;

/**
 * @author Anton Yolgin
 */

public class RestaurantRestControllerTest extends AbstractControllerTest{
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    RestaurantService service;

    @Test
    public void testGetAllWIthDishesAndVotes() throws Exception {
        mockMvc.perform(get(REST_URL + TEST_DATE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_VOTE_MATCHER.contentListMatcher(RestaurantUtil.getWithVote(RESTAURANTS, VOTES)));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentListMatcher(RESTAURANT3, RESTAURANT2, RESTAURANT1));
    }


    @Test
    public void testCreate() throws Exception {
        Restaurant created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Restaurant returned = RESTAURANT_MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        RESTAURANT_MATCHER.assertEquals(created, returned);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, created, RESTAURANT1), service.getAll());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_CREATED_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, RESTAURANT1), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT2, updated), service.getAll());
    }


}