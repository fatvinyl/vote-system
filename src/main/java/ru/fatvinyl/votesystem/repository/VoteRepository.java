package ru.fatvinyl.votesystem.repository;

import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;

/**
 * @author Anton Yolgin
 */

public interface VoteRepository {

    Vote create(int restaurantId);

    boolean increment(LocalDate date, int restaurantId);

    boolean decrement(LocalDate date, int restaurantId);

    Vote get(int id);

}
