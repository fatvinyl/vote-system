package ru.fatvinyl.votesystem.service;

import ru.fatvinyl.votesystem.model.Vote;

/**
 * @author Anton Yolgin
 */

public interface VoteService {

    Vote save(int restaurantId, int userId);

    void update(int voteId, int userId);

    void delete(int voteId, int userId);

    Vote get(int id);

}
