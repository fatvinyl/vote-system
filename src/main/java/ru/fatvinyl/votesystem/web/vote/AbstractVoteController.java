package ru.fatvinyl.votesystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.AuthorizedUser;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.service.VoteService;

import java.time.LocalTime;

import static ru.fatvinyl.votesystem.util.VoteUtil.checkVotingTime;

/**
 * @author Anton Yolgin
 */


public abstract class AbstractVoteController {
    private static Logger LOG = LoggerFactory.getLogger(AbstractVoteController.class);

    @Autowired
    private VoteService service;

    Vote create(int restaurantId, LocalTime currentTime) {
        checkVotingTime(currentTime);
        int userId = AuthorizedUser.id();
        LOG.info("create vote for Restaurant {} for User {}", restaurantId, userId);
        Vote vote = new Vote(1, restaurantId);
        return service.save(vote, userId);
    }

    /**
     * Increments amount votes.
     * The update is done at the service level.
     *
     * @param vote The vote parameter that will be updated in the service layer.
     *
     */
    Vote increment(Vote vote, int id, LocalTime currentTime) {
        checkVotingTime(currentTime);
        int userId = AuthorizedUser.id();
        LOG.info("update vote {} for User {}", vote, userId);
        return service.increment(vote, userId);
    }

    Vote decrement(Vote vote, int id, LocalTime currentTime) {
        checkVotingTime(currentTime);
        int userId = AuthorizedUser.id();
        LOG.info("decrement vote {} for User {}", vote, userId);
        return service.decrement(vote, userId);
    }

}
