package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.repository.VoteDAO;


import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;
import static ru.fatvinyl.votesystem.util.VoteUtil.checkVotingTime;

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
    public Vote update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return dao.save(vote, userId);
    }

//    @Transactional
//    @Override
//    public void delete(int voteId, int userId) {
//        checkVotingTime();
//        checkNotFoundWithId(dao.delete(voteId, userId), voteId);
//    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(dao.get(id), id);
    }

}
