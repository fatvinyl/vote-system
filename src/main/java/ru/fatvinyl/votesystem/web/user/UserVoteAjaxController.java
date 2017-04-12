package ru.fatvinyl.votesystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fatvinyl.votesystem.AuthorizedUser;
import ru.fatvinyl.votesystem.model.User;
import ru.fatvinyl.votesystem.to.UserVote;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping("/ajax/profile/user_vote")
public class UserVoteAjaxController extends AbstractUserController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public UserVote get() {
          User user = super.get(AuthorizedUser.id());
        if (user.getVote() == null) {
            return new UserVote(user.getId(), null);
        }
       return new UserVote(user.getId(), user.getVote().getId());
    }
}
