package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.Role;
import ru.fatvinyl.votesystem.model.User;

import java.util.Arrays;

import static ru.fatvinyl.votesystem.UserTestData.*;


/**
 * @author Anton Yolgin
 */


public class UserRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    protected UserRepository repository;

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "NewUser", "new@test.com", "newPassword", Role.ROLE_USER);
        User created = repository.save(newUser);
        newUser.setId(created.getId());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER1, USER2), repository.getAll());
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(3);
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), repository.getAll());
    }

    @Test
    public void testGet() throws Exception {
        User actual = repository.get(ADMIN_ID);
        USER_MATCHER.assertEquals(ADMIN, actual);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User actual = repository.getByEmail("admin@mail.com");
        USER_MATCHER.assertEquals(ADMIN, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), repository.getAll());
    }


}