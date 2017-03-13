package ru.fatvinyl.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.repository.VoteRepository;


import static ru.fatvinyl.votesystem.util.ValidationUtil.checkNotFoundWithId;
import static ru.fatvinyl.votesystem.util.ValidationUtil.checkVotingTime;

/**
 * @author Anton Yolgin
 */

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

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
