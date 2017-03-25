package ru.fatvinyl.votesystem.util;

import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.util.exception.VotingTimeOverException;

import java.time.LocalTime;

/**
 * @author Anton Yolgin
 */


public class VoteUtil {

    public static boolean isNew(Vote vote) {
        if (vote.getAmount() == null) {
            return true;
        } else {
            return false;
        }
    }

    public static void checkVotingTime() {
        LocalTime current = LocalTime.now();
        LocalTime deadLine = LocalTime.parse("11:00");
        if (current.isAfter(deadLine)) {
            throw new VotingTimeOverException("The voting time was over");
        }
    }

}
