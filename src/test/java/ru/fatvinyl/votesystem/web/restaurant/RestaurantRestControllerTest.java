package ru.fatvinyl.votesystem.web.restaurant;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
import static ru.fatvinyl.votesystem.TestUtil.userHttpBasic;
import static ru.fatvinyl.votesystem.UserTestData.ADMIN;
import static ru.fatvinyl.votesystem.VoteTestData.VOTES;

/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantRestControllerTest extends AbstractControllerTest{
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    RestaurantService service;

    @Test
    public void test1GetAllWIthDishesAndVotes() throws Exception {
        mockMvc.perform(get(REST_URL + TEST_DATE)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_VOTE_MATCHER.contentListMatcher(RestaurantUtil.getWithVote(RESTAURANTS, VOTES)));
    }

    @Test
    public void test2GetAll() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentListMatcher(RESTAURANT3, RESTAURANT7, RESTAURANT2, RESTAURANT4, RESTAURANT5, RESTAURANT6, RESTAURANT1));
    }


    @Test
    public void test3Create() throws Exception {
        Restaurant created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created)));

        Restaurant returned = RESTAURANT_MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        RESTAURANT_MATCHER.assertEquals(created, returned);
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT7, RESTAURANT2, RESTAURANT4, RESTAURANT5, created, RESTAURANT6, RESTAURANT1), service.getAll());
    }

    @Test
    public void test4Delete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_CREATED_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT7, RESTAURANT2, RESTAURANT4, RESTAURANT5, RESTAURANT6, RESTAURANT1), service.getAll());
    }

    @Test
    public void test5Update() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT3, RESTAURANT7, RESTAURANT2, updated, RESTAURANT4, RESTAURANT5, RESTAURANT6), service.getAll());
    }


}