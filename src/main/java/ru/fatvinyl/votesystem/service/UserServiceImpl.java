package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.AuthorizedUser;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.repository.UserDAO;
import ru.fatvinyl.votesystem.to.UserTo;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;

import java.util.List;

import static ru.fatvinyl.votesystem.util.UserUtil.prepareToSave;
import static ru.fatvinyl.votesystem.util.UserUtil.updateFromTo;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFound;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Anton Yolgin
 */

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO dao;

    @Transactional
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return dao.save(prepareToSave(user));
    }

    @Transactional
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        dao.save(prepareToSave(user));
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        dao.save(user);
        dao.save(prepareToSave(user));
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

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = dao.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        dao.save(user);
    }
}
