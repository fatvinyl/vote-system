package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;

/**
 * @author Anton Yolgin
 */

public interface VoteRepository {

    Vote save(int restaurantId, int userId);

    boolean update(int voteId, int userId);

    boolean delete(int voteId, int userId);

    Vote get(int id);

}
