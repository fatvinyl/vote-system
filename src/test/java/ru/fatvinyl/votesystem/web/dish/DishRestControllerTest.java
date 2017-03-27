package ru.fatvinyl.votesystem.web.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.service.DishService;
import ru.fatvinyl.votesystem.web.AbstractControllerTest;
import ru.fatvinyl.votesystem.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fatvinyl.votesystem.DishTestData.*;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;

/**
 * @author Anton Yolgin
 */
public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Autowired
    DishService service;

    @Test
    public void testCreate() throws Exception {
        Dish created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Dish returned = DISHES_MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        DISHES_MATCHER.assertEquals(created, returned);
        DISHES_MATCHER.assertCollectionEquals(Arrays.asList(created, DISH_1, DISH_2), service.getAllByDate(TEST_DATE, 1));
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();

        mockMvc.perform(put(REST_URL + DISH1_ID + "&" + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(DISH1_ID, RESTAURANT1_ID));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH6_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        DISHES_MATCHER.assertCollectionEquals(Arrays.asList(DISH_5), service.getAllByDate(TEST_DATE, 3));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISHES_MATCHER.contentMatcher(DISH_1));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "by?date=2017-01-11&restaurantId=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(DISHES_MATCHER.contentListMatcher(DISH_1, DISH_2));
    }

}