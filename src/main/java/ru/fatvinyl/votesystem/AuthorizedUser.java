package ru.fatvinyl.votesystem;

/**
 * @author Anton Yolgin
 */

public class AuthorizedUser {

    private static final int ADMIN_ID = 1;
    private static final int USER_ID = 2;

    public static int id() {
        return ADMIN_ID;
    }
}
