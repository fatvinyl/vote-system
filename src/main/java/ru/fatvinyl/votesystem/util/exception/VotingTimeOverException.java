package ru.fatvinyl.votesystem.util.exception;

/**
 * @author Anton Yolgin
 */

public class VotingTimeOverException extends RuntimeException {
    public VotingTimeOverException(String message) {
        super(message);
    }
}
