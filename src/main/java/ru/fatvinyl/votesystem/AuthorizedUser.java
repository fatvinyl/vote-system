package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.model.User;

import static java.util.Objects.requireNonNull;

/**
 * @author Anton Yolgin
 */

public class AuthorizedUser {

    private static final int ADMIN_ID = 1;
    private static final int USER_ID = 2;

    private User user;

    public AuthorizedUser(User user) {
        this.user = user;
    }

//    public static AuthorizedUser get() {
//        AuthorizedUser user = safeGet();
//        requireNonNull(user, "No authorized user found");
//        return user;
//    }

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public static int id() {
        return USER_ID;
    }




}
