package ru.fatvinyl.votesystem.web.user;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import ru.fatvinyl.votesystem.TestUtil;
import ru.fatvinyl.votesystem.model.Role;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.web.AbstractControllerTest;
import ru.fatvinyl.votesystem.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fatvinyl.votesystem.UserTestData.*;

/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfileRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL =  ProfileRestController.REST_URL + '/';


    @Test
    public void test1Get() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentMatcher(USER1)));
    }

    @Test
    public void test2Update() throws Exception {
        User updated = new User(USER_ID1, "newName", "newemail@ya.ru", "newPassword", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        USER_MATCHER.assertEquals(updated, new User(userService.getByEmail("newemail@ya.ru")));
    }

    @Test
    public void test3Delete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isOk());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER2), userService.getAll());
    }


}