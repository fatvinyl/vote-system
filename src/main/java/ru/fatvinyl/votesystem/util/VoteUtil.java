package ru.fatvinyl.votesystem.util;

import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.util.exception.IllegalValueException;
import ru.fatvinyl.votesystem.util.exception.VotingTimeOverException;

import java.time.LocalTime;

/**
 * @author Anton Yolgin
 */

public class VoteUtil {

    /**
     * @throws VotingTimeOverException if the current time is after 11:00
     */
    public static void checkVotingTime(LocalTime currentTime) {
        LocalTime deadLine = LocalTime.parse("11:00");
        if (currentTime.isAfter(deadLine)) {
            throw new VotingTimeOverException("The voting time was over");
        }
    }

    /**
     * @param vote the object to update
     * @throws IllegalArgumentException if the vote is {@code null}
     */
    public static Vote incrementVote(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        int voteIncremented = vote.getAmount() + 1;
        vote.setAmount(voteIncremented);
        return vote;
    }

    /**
     * @param vote the object to update
     * @throws IllegalArgumentException if the vote is {@code null}
     * @throws IllegalValueException if (vote.getAmount() <= 0)
     */
    public static Vote decrementVote(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        int amount = vote.getAmount();
        if (amount > 0) {
            int voteIncremented = vote.getAmount() - 1;
            vote.setAmount(voteIncremented);
            return vote;
        }
        throw new IllegalValueException("amount must be greater than zero");
    }

}
