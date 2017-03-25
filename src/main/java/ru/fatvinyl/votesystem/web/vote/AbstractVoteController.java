package ru.fatvinyl.votesystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.AuthorizedUser;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.service.VoteService;

/**
 * @author Anton Yolgin
 */


public abstract class AbstractVoteController {
    private static Logger LOG = LoggerFactory.getLogger(AbstractVoteController.class);

    @Autowired
    private VoteService service;

    Vote create(int restaurantId) {
        int userId = AuthorizedUser.id();
        LOG.info("create vote for Restaurant {} for User {}", restaurantId, userId);
        return service.save(restaurantId, userId);
    }

    void update(int voteId, int userId) {

    }

    void delete(int voteId, int userId) {

    }

    Vote get(int id) {
        return null;
    }
}
