package ru.fatvinyl.votesystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fatvinyl.votesystem.AuthorizedUser;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.service.VoteService;

import static ru.fatvinyl.votesystem.util.ValidationUtil.checkIdConsistent;

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
        Vote vote = new Vote(1, restaurantId);
        return service.save(vote, userId);
    }

    Vote update(Vote vote, int id) {
        checkIdConsistent(vote, id);
        int userId = AuthorizedUser.id();
        LOG.info("update vote {} for User {}", vote, userId);
        return service.update(vote, userId);
    }

    void delete(int voteId) {
        int userId = AuthorizedUser.id();
        LOG.info("delete Vote {} for User {}", voteId, userId);
        service.delete(voteId, userId);
    }

    Vote get(int id) {
        LOG.info("get Vote {}", id);
        return service.get(id);
    }
}
