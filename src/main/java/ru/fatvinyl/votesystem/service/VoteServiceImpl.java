package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.repository.VoteDAO;


import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;
import static ru.fatvinyl.votesystem.util.VoteUtil.decrementVote;
import static ru.fatvinyl.votesystem.util.VoteUtil.incrementVote;

/**
 * @author Anton Yolgin
 */

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDAO dao;

    @Transactional
    @Override
    public Vote save(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return dao.save(vote, userId);
    }

    @Transactional
    @Override
    public Vote increment(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return dao.save(incrementVote(vote), userId);
    }

    @Transactional
    @Override
    public Vote decrement(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return dao.save(decrementVote(vote), userId);
    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(dao.get(id), id);
    }

}
