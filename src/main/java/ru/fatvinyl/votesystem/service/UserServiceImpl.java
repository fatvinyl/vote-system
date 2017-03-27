package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.repository.UserDAO;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;

import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFound;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Anton Yolgin
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Transactional
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return dao.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        dao.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        checkNotFoundWithId(dao.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(dao.get(id), id);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(dao.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }
}
