package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.User;

import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

}
