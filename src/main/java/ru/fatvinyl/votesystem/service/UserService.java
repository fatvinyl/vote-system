package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.User;

import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface UserService {

    User save(User user);

    void update(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
