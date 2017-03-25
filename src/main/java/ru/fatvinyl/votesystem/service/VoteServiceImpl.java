package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.repository.VoteRepository;


import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;
import static ru.fatvinyl.votesystem.util.VoteUtil.checkVotingTime;

/**
 * @author Anton Yolgin
 */

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;


    //new method
    @Override
    public Vote save(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        checkVotingTime();
        return repository.save(vote, userId);
    }

    //new method
    @Override
    public Vote update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        checkVotingTime();
        int voteIncremented = vote.getAmount() + 1;
        vote.setAmount(voteIncremented);
        return checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }


    @Override
    public Vote save(int restaurantId, int userId) {
        checkVotingTime();
        return repository.save(restaurantId, userId);
    }

    @Override
    public void update(int voteId, int userId) {
        checkVotingTime();
        checkNotFoundWithId(repository.update(voteId, userId), voteId);
    }

    @Override
    public void delete(int voteId, int userId) {
        checkVotingTime();
        checkNotFoundWithId(repository.delete(voteId, userId), voteId);
    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

}
