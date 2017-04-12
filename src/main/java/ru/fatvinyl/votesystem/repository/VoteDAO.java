package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

public interface VoteDAO {

    Vote save(Vote vote, int userId);

    Vote decrement(Vote vote, int userId);

    boolean delete(int voteId, int userId);

    Vote get(int id);

    List<Vote> getAllByDate(LocalDate date);

}
