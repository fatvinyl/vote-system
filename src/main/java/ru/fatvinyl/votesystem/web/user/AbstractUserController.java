package ru.fatvinyl.votesystem.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.service.UserService;
import ru.fatvinyl.votesystem.to.UserTo;

import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkIdConsistent;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNew;

/**
 * @author Anton Yolgin
 */

public class AbstractUserController {
    protected final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    User create(User user) {
        checkNew(user);
        LOG.info("create {}", user);
        return service.save(user);
    }

    void update(User user, int id) {
        LOG.info("update {}", user);
        checkIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        LOG.info("update " + userTo);
        checkIdConsistent(userTo, id);
//        checkModificationAllowed(userTo.getId());
        service.update(userTo);
    }

    void delete(int id) {
        LOG.info("delete {}", id);
        service.delete(id);
    }

    User get(int id) {
        LOG.info("get {}", id);
        return service.get(id
        );
    }

    User getByEmail(String email) {
        LOG.info("getUserByEmail {}", email);
        return service.getByEmail(email);
    }

    List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }



}
