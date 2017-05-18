package ru.fatvinyl.votesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Role;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.util.PasswordUtil;

import java.util.Objects;

/**
 * @author Anton Yolgin
 */

public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int ADMIN_ID = 1;
    public static final int USER_ID1 = 2;
    public static final int USER_ID2 = 3;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@mail.com", "admin", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER1 = new User(USER_ID1, "User_1", "user1@mail.com", "password_1", Role.ROLE_USER);
    public static final User USER2 = new User(USER_ID2, "User_2", "user2@mail.com", "password_2", Role.ROLE_USER);


    public static final ModelMatcher<User> USER_MATCHER = ModelMatcher.of(User.class,
            ((expected, actual) -> expected == actual ||
                    (comparePassword(expected.getPassword(), actual.getPassword()))
                            &&   Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
            );

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

}
