package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    Vote save(int restaurantId, int userId);

    boolean update(int voteId, int userId);

    boolean delete(int voteId, int userId);

    Vote get(int id);

    List<Vote> getAllByDate(LocalDate date);

}
