package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.repository.UserRepository;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;

import java.util.List;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFound;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Anton Yolgin
 */

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
