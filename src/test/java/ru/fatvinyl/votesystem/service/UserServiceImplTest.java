package ru.fatvinyl.votesystem.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.fatvinyl.votesystem.model.Role;
import ru.fatvinyl.votesystem.model.User;

import java.util.Arrays;

import static ru.fatvinyl.votesystem.UserTestData.*;


/**
 * @author Anton Yolgin
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void test1Save() throws Exception {
        User newUser = new User(null, "NewUser", "new@test.com", "newPassword", Role.ROLE_USER);
        User created = service.save(newUser);
        newUser.setId(created.getId());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER1, USER2), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void test2DuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user1@mail.com", "newPass", Role.ROLE_USER));
    }

    @Test
    public void test3Delete() throws Exception {
        service.delete(4);
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), service.getAll());
    }

    @Test
    public void test4Get() throws Exception {
        User actual = service.get(ADMIN_ID);
        USER_MATCHER.assertEquals(ADMIN, actual);
    }

    @Test
    public void test5GetByEmail() throws Exception {
        User actual = service.getByEmail("admin@mail.com");
        USER_MATCHER.assertEquals(ADMIN, actual);
    }

    @Test
    public void test6GetAll() throws Exception {
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), service.getAll());
    }


}