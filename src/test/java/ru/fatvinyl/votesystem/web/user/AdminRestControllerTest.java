package ru.fatvinyl.votesystem.web.user;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.fatvinyl.votesystem.TestUtil;
import ru.fatvinyl.votesystem.model.Role;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.web.AbstractControllerTest;
import ru.fatvinyl.votesystem.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fatvinyl.votesystem.UserTestData.*;

/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    public void test1Get() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentMatcher(ADMIN));
    }

    @Test
    public void test2GetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + USER1.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentMatcher(USER1));
    }

    @Test
    public void test3GetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentListMatcher(ADMIN, USER1, USER2)));
    }

    @Test
    public void test4Delete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID2))
                .andDo(print())
                .andExpect(status().isOk());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1), userService.getAll());
    }

    @Test
    public void test5Create() throws Exception {
        User expected = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        User returned = USER_MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        USER_MATCHER.assertEquals(expected, returned);
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, expected, USER1), userService.getAll());
    }

    @Test
    public void test6Update() throws Exception {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        mockMvc.perform(put(REST_URL + USER_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        USER_MATCHER.assertEquals(updated, userService.get(USER_ID1));
    }


}